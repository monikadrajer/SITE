package org.sitenv.portlets.smtpdirectedgetransport.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
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
import org.sitenv.portlets.smtpdirectedgetransport.models.SimpleEmailMessageAttachmentAttributes;
import org.sitenv.portlets.smtpdirectedgetransport.models.SimpleEmailMessageAttributes;
import org.sitenv.portlets.smtpdirectedgetransport.services.DirectEdgeSmtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.multipart.MultipartActionRequest;

@Controller
@RequestMapping("VIEW")
public class DirectEdgeReceiveController extends BaseController
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
	public void uploadCCDADirectEdgeReceive(MultipartActionRequest request, ActionResponse response, @RequestParam("ccdauploadfile") final MultipartFile file, @RequestParam("ccdauploademail") final String fromEmail) throws IOException, JSONException  {
		Boolean uploadSuccess = false;
		String fileName = null;
		InputStream attachmentInputStream = null;
		//String smtppswrd = FileUtils.readFileToString(new File(props.getProperty("smtppswdPath")));
		String smtppswrd = "providerpass";
		response.setRenderParameter("javax.portlet.action", "uploadCCDADirectEdgeReceive");
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
				//DataSource ccdaFile = new InputStreamDataSource(fileName, "text/plain; charset=UTF-8", file.getInputStream());
				attachmentInputStream = file.getInputStream();
				DataSource ccdaFile = new ByteArrayDataSource(attachmentInputStream, "text/plain; charset=UTF-8");
				SimpleEmailMessageAttributes emailAttributes = populateCCDAMessageAttributes(fileName, fromEmail, ccdaFile, file.getContentType());
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("files", directEdgeRecieveResults.getFileJson());
		map.put("result", directEdgeRecieveResults.getUploadResult());
		return new ModelAndView("genericResultJsonView", map);
	}

	@ActionMapping(params = "javax.portlet.action=precannedCCDADirectEdgeReceive")
	public void precannedCCDADirectEdgeReceive(ActionRequest request, ActionResponse response, @RequestParam("precannedfilepath") final String precannedfile, @RequestParam("fromemail") final String fromEmail) throws IOException, JSONException {
		String sampleCcdaDir = props.getProperty("sampleCcdaDir");
		InputStream attachmentInputStream = null;
		response.setRenderParameter("javax.portlet.action", "precannedCCDADirectEdgeReceive");
		String serverFilePath = sampleCcdaDir + "/" + precannedfile;
		String domain = fromEmail.split(delimiter)[1];
		directEdgeRecieveResults.setPrecannedResult(new JSONObject());
		try {
			//final String smtppswrd = FileUtils.readFileToString(new File(props.getProperty("smtppswdPath")));
			//final String decyptedPass = new DesEncrypter(ENCRYPTEDKEY).decrypt(smtppswrd);
			FileDataSource fileDataSource = new FileDataSource(serverFilePath);
			attachmentInputStream = fileDataSource.getInputStream();
			SimpleEmailMessageAttributes emailAttributes = populateCCDAMessageAttributes(precannedfile, fromEmail, fileDataSource, fileDataSource.getContentType());
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
	
	@RequestMapping(params = "javax.portlet.action=precannedCCDADirectEdgeReceive")
	public ModelAndView processPrecannedCCDADirectEdgeReceive(RenderRequest request, Model model)
			throws IOException {
		Map<String, JSONObject> map = new HashMap<String, JSONObject>();
		map.put("files", null);
		map.put("result", directEdgeRecieveResults.getPrecannedResult());
		return new ModelAndView("genericResultJsonView", map);
	}

	@ActionMapping(params = "javax.portlet.action=smtpSearch")
	public void smtpSearch(ActionRequest request, ActionResponse response, @RequestParam("smtpsearchinput") final String searchKeyWord) throws IOException, JSONException {	
		response.setRenderParameter("javax.portlet.action", "smtpSearch");
		JSONArray searchResults = new JSONArray();
		try {
			for(SimpleEmailMessageAttributes searchResult : directEdgeSmtpService.searchEmail(props, searchKeyWord)) {
				JSONObject messageResult = new JSONObject();
				messageResult.put("messageSubject", searchResult.getMessageSubject());
				messageResult.put("messageFrom", searchKeyWord);
				messageResult.put("messageReceivedDate", searchResult.getRecievedDate());
				messageResult.put("messageSentDate", searchResult.getSentDate());
				messageResult.put("messageBody", searchResult.getMessageBody());
				if(searchResult.hasAttachment()) {
					JSONArray messageAttachments = new JSONArray();
					for(SimpleEmailMessageAttachmentAttributes attachmentAttributes : searchResult.getAttachments()) {
						JSONObject attachment = new JSONObject();
						attachment.put("attachmentName", attachmentAttributes.getAttachmentName());
						attachment.put("attachmentBody", convertInputStreamToString(attachmentAttributes.getAttachment(), attachmentAttributes.getAttachmentContentType()));
						messageAttachments.put(attachment);
					}
					messageResult.put("attachments", messageAttachments);
				}
				
				searchResults.put(messageResult);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		directEdgeRecieveResults.setSearchResult(searchResults);
		//statisticsManager.addDirectReceive(domain, false, true, false);
	}

	@RequestMapping(params = "javax.portlet.action=smtpSearch")
	public ModelAndView processSMTPSearch(RenderRequest request, Model model)
			throws IOException {
		Map<String, JSONArray> map = new HashMap<String, JSONArray>();
		map.put("searchResults", directEdgeRecieveResults.getSearchResult());
		return new ModelAndView("directEdgeSMTPSearchResultsJsonView", map);
	}
	
	private SimpleEmailMessageAttributes populateCCDAMessageAttributes(
			String fileName, String fromEmail, DataSource ccdaFile, String contentType) throws IOException {
		SimpleEmailMessageAttributes emailAttributes = new SimpleEmailMessageAttributes();
		List<SimpleEmailMessageAttachmentAttributes> attachments = new ArrayList<SimpleEmailMessageAttachmentAttributes>();
		SimpleEmailMessageAttachmentAttributes attachmentAttributes = new SimpleEmailMessageAttachmentAttributes();
		attachmentAttributes.setAttachment(ccdaFile);
		attachmentAttributes.setAttachmentContentType(contentType);
		attachmentAttributes.setAttachmentContentType(contentType);
		attachments.add(attachmentAttributes);
		emailAttributes.setAttachments(attachments);
		emailAttributes.setMessageSubject(cannedMessageSubject);
		emailAttributes.setFrom(fromEmail);
		emailAttributes.setTo(hisptoemailaddress);
		emailAttributes.setMessageBody(cannedMessageBody);
		return emailAttributes;
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

	private String convertInputStreamToString(DataSource attachment, String charset) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(attachment.getInputStream(), "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = "";
		String result = "";
		try {
			while((line = bufferedReader.readLine()) != null)
				result += line;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		IOUtils.closeQuietly(bufferedReader);
		IOUtils.closeQuietly(inputStreamReader);
		return result; 
	}
	
}
