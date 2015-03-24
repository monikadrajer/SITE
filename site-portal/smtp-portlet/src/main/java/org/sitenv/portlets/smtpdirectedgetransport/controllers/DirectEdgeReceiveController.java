package org.sitenv.portlets.smtpdirectedgetransport.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SearchTerm;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sitenv.common.statistics.manager.StatisticsManager;
import org.sitenv.common.utilities.controller.BaseController;
import org.sitenv.portlets.smtpdirectedgetransport.DirectEdgeReceiveResults;
import org.sitenv.portlets.smtpdirectedgetransport.models.SimpleEmailMessageAttributes;
import org.sitenv.portlets.smtpdirectedgetransport.services.DirectEdgeSmtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.multipart.MultipartActionRequest;

@Controller
@RequestMapping("VIEW")
public class DirectEdgeReceiveController  extends BaseController
{
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;    // 10MB 
	private static final String ENCRYPTEDKEY = "sitplatform@1234";
	private static final String delimiter = "@";
	private static final String cannedMessageSubject = "SITE direct email test";
	private static final String cannedMessageBody = "Dear User," + "\r\nAttached is the C-CDA document you have selected.";
	private String hisptoemailaddress;
	@Autowired
	private DirectEdgeReceiveResults directEdgeRecieveResults;
	@Autowired
	private StatisticsManager statisticsManager;
	@Autowired
	DirectEdgeSmtpService directEdgeSmtpService;

	{
		if (this.props == null)
		{
			try {
				this.loadProperties();
				hisptoemailaddress = props.getProperty("hisptoemailaddress");
			} catch (IOException e) {
				throw new ExceptionInInitializerError(e);
			}
		}
	}

	@ActionMapping(params = "javax.portlet.action=uploadCCDADirectEdgeReceive")
	public void uploadCCDADirectEdgeReceive(MultipartActionRequest request, ActionResponse response) throws IOException, JSONException  {
		Boolean uploadSuccess = false;
		String fileName = null;
		InputStream attachmentInputStream = null;
		//String smtppswrd = FileUtils.readFileToString(new File(props.getProperty("smtppswdPath")));
		String smtppswrd = "providerpass";
		MultipartFile file = request.getFile("ccdauploadfile");
		response.setRenderParameter("javax.portlet.action", "uploadCCDADirectEdgeReceive");
		String fromEmail = request.getParameter("ccdauploademail");
		String domain = fromEmail.split(delimiter)[1];

		directEdgeRecieveResults.setFileJson(new JSONArray());
		directEdgeRecieveResults.setUploadResult(new JSONObject());
		try{
			JSONObject jsono = new JSONObject();
			jsono.put("name", file.getOriginalFilename());
			jsono.put("size", file.getSize());

			directEdgeRecieveResults.getFileJson().put(jsono);
			fileName = new File(file.getOriginalFilename()).getName();
			if (file.getSize() > MAX_FILE_SIZE)
			{
				throw new FileUploadException("Uploaded file exceeded maxinum number of bytes.");
			}
			uploadSuccess = true;
		} catch (FileUploadException e) {
			if(e.getMessage().endsWith("bytes.")) {
				statisticsManager.addDirectReceive(domain, true, false, true);
				directEdgeRecieveResults.getUploadResult().put("IsSuccess", "false");
				directEdgeRecieveResults.getUploadResult().put("ErrorMessage", "Maxiumum file size exceeeded. " + 
						"Please return to the previous page and select a file that is less than "
						+ MAX_FILE_SIZE / 1024 / 1024 + "MB(s).");
			} else {
				statisticsManager.addDirectReceive(domain, true, false, true);
				directEdgeRecieveResults.getUploadResult().put("IsSuccess", "false");
				directEdgeRecieveResults.getUploadResult().put("ErrorMessage", "There was an error uploading the file: " + e.getMessage());
			}
		} 

		if(uploadSuccess)
		{
			try {  
				attachmentInputStream = file.getInputStream();
				SimpleEmailMessageAttributes emailAttributes = populateCCDAMessageAttributes(fileName, fromEmail, IOUtils.toByteArray(attachmentInputStream));
				directEdgeSmtpService.sendEmail(props, emailAttributes);
				directEdgeRecieveResults.getUploadResult().put("IsSuccess", "true");
				directEdgeRecieveResults.getUploadResult().put("ErrorMessage", "Mail sent.");
				statisticsManager.addDirectReceive(domain, true, false, false);
			} catch (MessagingException e) {
				statisticsManager.addDirectReceive(domain, true, false, true);
				directEdgeRecieveResults.getUploadResult().put("IsSuccess", "false");
				directEdgeRecieveResults.getUploadResult().put("ErrorMessage", "Failed to send email due to eror: " + e.getMessage());
			}finally {
				IOUtils.closeQuietly(attachmentInputStream);
			} 
		}
	}

	@RequestMapping(params = "javax.portlet.action=uploadCCDADirectEdgeReceive")
	public ModelAndView processUploadCCDADirectEdgeReceive(RenderRequest request, Model model){
		Map map = new HashMap();
		map.put("files", directEdgeRecieveResults.getFileJson());
		map.put("result", directEdgeRecieveResults.getUploadResult());
		return new ModelAndView("genericResultJsonView", map);
	}

	@ActionMapping(params = "javax.portlet.action=precannedCCDADirectEdgeReceive")
	public void precannedCCDADirectEdgeReceive(ActionRequest request, ActionResponse response) throws IOException, JSONException {
		String sampleCcdaDir = props.getProperty("sampleCcdaDir");
		String precannedfile = request.getParameter("precannedfilepath");
		InputStream attachmentInputStream = null;
		response.setRenderParameter("javax.portlet.action", "precannedCCDADirectEdgeReceive");
		String serverFilePath = sampleCcdaDir + "/" + precannedfile;
		String fromEmail = request.getParameter("fromemail");
		String domain = fromEmail.split(delimiter)[1];
		directEdgeRecieveResults.setPrecannedResult(new JSONObject());
		try {
			//final String smtppswrd = FileUtils.readFileToString(new File(props.getProperty("smtppswdPath")));
			//final String decyptedPass = new DesEncrypter(ENCRYPTEDKEY).decrypt(smtppswrd);
			attachmentInputStream = new FileDataSource(serverFilePath).getInputStream();
			SimpleEmailMessageAttributes emailAttributes = populateCCDAMessageAttributes(precannedfile, fromEmail, IOUtils.toByteArray(attachmentInputStream));
			directEdgeSmtpService.sendEmail(props, emailAttributes);
			setResultMessage(true, "MailSent");
			statisticsManager.addDirectReceive(domain, false, true, false);
		} catch (MessagingException e) {
			setResultMessage(false, "Failed to send email due to eror: " + e.getMessage());
			statisticsManager.addDirectReceive(domain, false, true, true);
		}finally {
			IOUtils.closeQuietly(attachmentInputStream);
		}
	}
	
	private SimpleEmailMessageAttributes populateCCDAMessageAttributes(
			String fileName, String fromEmail, byte[] ccdaFile) {
		SimpleEmailMessageAttributes emailAttributes = new SimpleEmailMessageAttributes();
		emailAttributes.setAttachment(ccdaFile);
		emailAttributes.setAttachmentName(fileName);
		emailAttributes.setMessageSubject(cannedMessageSubject);
		emailAttributes.setFrom(fromEmail);
		emailAttributes.setTo(hisptoemailaddress);
		emailAttributes.setMessageBody(cannedMessageBody);
		return emailAttributes;
	}
	
	@RequestMapping(params = "javax.portlet.action=precannedCCDADirectEdgeReceive")
	public ModelAndView processPrecannedCCDADirectEdgeReceive(RenderRequest request, Model model)
			throws IOException {
		Map map = new HashMap();
		map.put("files", null);
		map.put("result", directEdgeRecieveResults.getPrecannedResult());
		return new ModelAndView("genericResultJsonView", map);
	}

	@ActionMapping(params = "javax.portlet.action=smtpSearch")
	public void smtpSearch(ActionRequest request, ActionResponse response) throws IOException, JSONException {	
		response.setRenderParameter("javax.portlet.action", "smtpSearch");
		Session session = getEmailSession(props);
		JSONArray messageJsonArray = searchEmail(session, request.getParameter("smtpsearchinput"));
		directEdgeRecieveResults.setSearchResult(messageJsonArray);
		//statisticsManager.addDirectReceive(domain, false, true, false);
	}

	@RequestMapping(params = "javax.portlet.action=smtpSearch")
	public ModelAndView processSMTPSearch(RenderRequest request, Model model)
			throws IOException {
		Map<String, JSONArray> map = new HashMap<String, JSONArray>();
		map.put("searchResults", directEdgeRecieveResults.getSearchResult());
		return new ModelAndView("directEdgeSMTPSearchResultsJsonView", map);
	}

	private void setResultMessage(boolean isSuccess, String message) throws JSONException {
		directEdgeRecieveResults.getPrecannedResult().put("IsSuccess", Boolean.toString(isSuccess));
		directEdgeRecieveResults.getPrecannedResult().put("ErrorMessage", message);
	}

	public StatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	public void setStatisticsManager(StatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}  

	private JSONArray searchEmail(Session session, final String keyword) {
		JSONArray messageJSONArray = new JSONArray();
		try {
			// connects to the message store
			Store store = session.getStore("pop3");
			store.connect();

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// creates a search criterion
			SearchTerm searchCondition = new SearchTerm() {
				@Override
				public boolean match(Message message) {
					try {
						Address[] addresses = message.getFrom();
						for(Address address : addresses) {
							InternetAddress iAddress = (InternetAddress)address;
							if(iAddress.getAddress().equals(keyword)){
								return true;
							}
						}
					} catch (MessagingException ex) {
						ex.printStackTrace();
					}
					return false;
				}
			};

			// performs search through the folder
			Message[] foundMessages = folderInbox.search(searchCondition);
			if(foundMessages.length > 0) {
				for (int i = 0; i < foundMessages.length; i++) {
					Message message = foundMessages[i];
					JSONObject messageJsonObject = new JSONObject();
					try {
						messageJsonObject.put("messageSubject", message.getSubject());
						messageJsonObject.put("messageFrom", keyword);
						messageJsonObject.put("messageReceivedDate", message.getReceivedDate());
						messageJsonObject.put("messageSentDate", message.getSentDate());
						Multipart mp;
						try {
							mp = (Multipart) message.getContent();
							for (int j = 0; j < mp.getCount(); j++) {
								BodyPart b = mp.getBodyPart(j);
								if(b.getDisposition() == null || b.getDisposition().equalsIgnoreCase(Part.INLINE)) {
									messageJsonObject.put("messageBody", b.getContent());
								}else if(b.getDescription() == null || b.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
									messageJsonObject.put("attachmentName", b.getFileName());
									if(b.getContent() instanceof String) {
										messageJsonObject.put("attachmentBody", b.getContent());
									}else {
										messageJsonObject.put("attachmentBody", convertInputStreamToString((InputStream)b.getContent()));
									}
									
								}
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					messageJSONArray.put(messageJsonObject);
				}
			}
			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider.");
			ex.printStackTrace();

		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store.");
			ex.printStackTrace();
		}
		return messageJSONArray;
	}

	private String convertInputStreamToString(InputStream inputStream) throws IOException {
		String result = IOUtils.toString(inputStream, "UTF-8");
		IOUtils.closeQuietly(inputStream);
		return result; 
	}

	private Session getEmailSession(final Properties props) {
		return Session.getInstance(props,
				new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("mail.smtp.user"), "providerpass");
			}
		});
	}
	
}
