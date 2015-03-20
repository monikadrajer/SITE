package org.sitenv.portlets.smtpdirectedgetransport.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
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
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sitenv.common.statistics.manager.StatisticsManager;
import org.sitenv.common.utilities.controller.BaseController;
import org.sitenv.portlets.smtpdirectedgetransport.DirectEdgeReceiveResults;
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
	private String hisptoemailaddress;
	@Autowired
	private DirectEdgeReceiveResults directEdgeRecieveResults;
	@Autowired
	private StatisticsManager statisticsManager;

	{
		if (this.props == null)
		{
			try {
				this.loadProperties();
				configureMailProperties();
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
		//String smtppswrd = FileUtils.readFileToString(new File(props.getProperty("smtppswdPath")));
		String smtppswrd = "providerpass";
		
		response.setRenderParameter("javax.portlet.action", "uploadCCDADirectEdgeReceive");
		MultipartFile file = request.getFile("ccdauploadfile");
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
		} catch (Exception e) {
			statisticsManager.addDirectReceive(domain, true, false, true);
			directEdgeRecieveResults.getUploadResult().put("IsSuccess", "false");
			directEdgeRecieveResults.getUploadResult().put("ErrorMessage", "There was an error saving the file: " + e.getMessage());
		}

		if(uploadSuccess)
		{
			try {  
				DataSource ccdaFile = new InputStreamDataSource(fileName, "text/plain; charset=UTF-8", file.getInputStream());			
				Message message = createMessage(hisptoemailaddress, fromEmail, fileName, ccdaFile);  
				Transport.send(message);
				directEdgeRecieveResults.getUploadResult().put("IsSuccess", "true");
				directEdgeRecieveResults.getUploadResult().put("ErrorMessage", "Mail sent.");
				statisticsManager.addDirectReceive(domain, true, false, false);
			} catch (MessagingException e) {
				statisticsManager.addDirectReceive(domain, true, false, true);
				directEdgeRecieveResults.getUploadResult().put("IsSuccess", "false");
				directEdgeRecieveResults.getUploadResult().put("ErrorMessage", "Failed to send email due to eror: " + e.getMessage());
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
		response.setRenderParameter("javax.portlet.action", "precannedCCDADirectEdgeReceive");
		String precannedfile = request.getParameter("precannedfilepath");
		String serverFilePath = sampleCcdaDir + "/" + precannedfile;
		String fromEmail = request.getParameter("fromemail");
		String domain = fromEmail.split(delimiter)[1];
		directEdgeRecieveResults.setPrecannedResult(new JSONObject());
		try {
			//final String smtppswrd = FileUtils.readFileToString(new File(props.getProperty("smtppswdPath")));
			//final String decyptedPass = new DesEncrypter(ENCRYPTEDKEY).decrypt(smtppswrd);
			DataSource ccdaFile = new FileDataSource(serverFilePath);  
			Message message = createMessage(hisptoemailaddress, fromEmail, ccdaFile.getName(), ccdaFile);
			Transport.send(message);
			setResultMessage(true, "MailSent");
			statisticsManager.addDirectReceive(domain, false, true, false);
		} catch (MessagingException e) {
			setResultMessage(false, "Failed to send email due to eror: " + e.getMessage());
			statisticsManager.addDirectReceive(domain, false, true, true);
		}		
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
	
	private Message createEmptyTestMessage(String fromEmail, String hisptoemailaddress) throws MessagingException, AddressException {
		Message message = new MimeMessage(this.getEmailSession(props));
		message.setFrom(new InternetAddress(fromEmail));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(hisptoemailaddress));
		message.setSubject("SITE direct email test");
		return message;
	}

	private void setResultMessage(boolean isSuccess, String message) throws JSONException {
		directEdgeRecieveResults.getPrecannedResult().put("IsSuccess", Boolean.toString(isSuccess));
		directEdgeRecieveResults.getPrecannedResult().put("ErrorMessage", message);
	}

	private class InputStreamDataSource implements DataSource {  
		private String name;  
		private String contentType;  
		private ByteArrayOutputStream baos;  
		InputStreamDataSource(String name, String contentType, InputStream inputStream) throws IOException {  
			this.name = name;  
			this.contentType = contentType;  
			baos = new ByteArrayOutputStream();  
			int read;  
			byte[] buff = new byte[2024];  
			while((read = inputStream.read(buff)) != -1) {  
				baos.write(buff, 0, read);  
			}  
		}  

		public String getContentType() {  
			return contentType;  
		}  

		public InputStream getInputStream() throws IOException {  
			return new ByteArrayInputStream(baos.toByteArray());  
		}  

		public String getName() {  
			return name;  
		}  

		public OutputStream getOutputStream() throws IOException {  
			throw new IOException("Cannot write to this read-only resource");  
		}  
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

	private String convertInputStreamToString(InputStream inputStream) throws JSONException {
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		try {
			while((line = bufferedReader.readLine()) != null)
				result += line;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result; 
	}

	private void configureMailProperties() {
		props.put("mail.smtp.host", props.getProperty("smtphostname"));
		props.put("mail.pop3.host", props.getProperty("smtphostname"));
		if(Boolean.valueOf(props.getProperty("smtpenablessl"))){
			props.put("mail.smtp.socketFactory.port", props.getProperty("smtpport"));
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.pop3.socketFactory.port","110");
			props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", props.getProperty("smtpport"));
		props.put("mail.pop3.port", "110");
		props.put("mail.pop3.socketFactory.fallback", "false");
	}

	private Session getEmailSession(Properties props) {
		final String smtpuser = props.getProperty("smtpusername");
		return Session.getInstance(props,
				new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpuser, "providerpass");
			}
		});
	}
	
	private Message createMessage(String toAddress, String fromEmail,
			String fileName, DataSource dataSource) throws MessagingException,
			AddressException {
		MimeBodyPart ccdaAttachment = setMessageAttachment(fileName, dataSource); 
		Message message = createEmptyTestMessage(fromEmail, toAddress);
		Multipart multiPart = new MimeMultipart();  
		setTestMessageBodyText(multiPart);  
		multiPart.addBodyPart(ccdaAttachment);  
		message.setContent(multiPart);
		return message;
	}

	private MimeBodyPart setMessageAttachment(String fileName,
			DataSource ccdaFile) throws MessagingException {
		MimeBodyPart ccdaAttachment = new MimeBodyPart();
		ccdaAttachment.setDataHandler(new DataHandler(ccdaFile));  
		ccdaAttachment.setFileName(fileName);
		return ccdaAttachment;
	}

	private void setTestMessageBodyText(Multipart multiPart)
			throws MessagingException {
		MimeBodyPart messageText = new MimeBodyPart();  
		messageText.setContent("Dear User," + "\r\nAttached is the C-CDA document you have selected.", "text/plain");  
		multiPart.addBodyPart(messageText);
	}
}
