package org.sitenv.portlets.directtransport.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
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
import org.sitenv.portlets.directtransport.DirectReceiveResults;
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
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;    // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;    // 10MB 
	private static final int REQUEST_SIZE = 1024 * 1024 * 11;    // 11MB
	private static final String SERVERFILEPATH_FLDNAME = "precannedfilepath";
	private static final String CUSTOMCCDAFILE_FLDNAME = "uploadccdafilecontent";
	private static final String ENCRYPTEDKEY = "sitplatform@1234";
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DirectReceiveResults directRecieveResults;
	@Autowired
	private StatisticsManager statisticsManager;
	
	@ActionMapping(params = "javax.portlet.action=uploadCCDADirectEdgeReceive")
	public void uploadCCDADirectEdgeReceive(MultipartActionRequest request, ActionResponse response) throws IOException, JSONException {
		
		Boolean uploadSuccess = false;
		
		String endPointEmail = null;
		String fileName = null;
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String delimiter = "@";

		
		String fromendpoint = props.getProperty("directFromEndpoint");
		
		String smtphostname = props.getProperty("smtphostname");
		String smtpport = props.getProperty("smtpport");
		String smtpuser = props.getProperty("smtpusername");
		//String smtppswrd = FileUtils.readFileToString(new File(props.getProperty("smtppswdPath")));
		String smtppswrd = "providerpass";
		String enableSSL = props.getProperty("smtpenablessl");
	
		response.setRenderParameter("javax.portlet.action", "uploadCCDADirectEdgeReceive");
		MultipartFile file = request.getFile("ccdauploadfile");
		
		endPointEmail = request.getParameter("ccdauploademail");
		String domain = endPointEmail.split(delimiter)[1];
		
		//fileJson = new JSONArray();
		directRecieveResults.setFileJson(new JSONArray());
		
		directRecieveResults.setUploadResult(new JSONObject());
		
		
		
		try{
			
			JSONObject jsono = new JSONObject();
			jsono.put("name", file.getOriginalFilename());
			jsono.put("size", file.getSize());

			directRecieveResults.getFileJson().put(jsono);
			
			fileName = new File(file.getOriginalFilename()).getName();
			
			if (file.getSize() > MAX_FILE_SIZE)
			{
				throw new FileUploadException("Uploaded file exceeded maxinum number of bytes.");
			}
			
			uploadSuccess = true;
			
		} catch (FileUploadException e) {
			if(e.getMessage().endsWith("bytes.")) {
				statisticsManager.addDirectReceive(domain, true, false, true);
				directRecieveResults.getUploadResult().put("IsSuccess", "false");
				directRecieveResults.getUploadResult().put("ErrorMessage", "Maxiumum file size exceeeded. " + 
						"Please return to the previous page and select a file that is less than "
						+ MAX_FILE_SIZE / 1024 / 1024 + "MB(s).");
			} else {
				statisticsManager.addDirectReceive(domain, true, false, true);
				directRecieveResults.getUploadResult().put("IsSuccess", "false");
				directRecieveResults.getUploadResult().put("ErrorMessage", "There was an error uploading the file: " + e.getMessage());
				
			}
		} catch (Exception e) {
			statisticsManager.addDirectReceive(domain, true, false, true);
			directRecieveResults.getUploadResult().put("IsSuccess", "false");
			directRecieveResults.getUploadResult().put("ErrorMessage", "There was an error saving the file: " + e.getMessage());
		}
		
		if(uploadSuccess)
		{
			try {
				
				MimeBodyPart ccdaAttachment = new MimeBodyPart();  
				
				
				DataSource ccdaFile = new InputStreamDataSource(fileName, "text/plain; charset=UTF-8", file.getInputStream());
				ccdaAttachment.setDataHandler(new DataHandler(ccdaFile));  
			    ccdaAttachment.setFileName(fileName); 
				
				Properties props = new Properties();
				
				//decrypt the password.
				
				//smtppswrd = new DesEncrypter(ENCRYPTEDKEY).decrypt(smtppswrd);
				
				props.put("mail.smtp.host", smtphostname);
				if(enableSSL.toUpperCase().equals("TRUE")){
					props.put("mail.smtp.socketFactory.port", smtpport);
					props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				}
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", smtpport);
		 		
				
				//java is stupid... java doesn't have true closure.
				final String user = smtpuser;
				final String passord = smtppswrd;
				
				Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user, passord);
						}
					});
	 
				Message message = new MimeMessage(session);
				
				message.setFrom(new InternetAddress(fromendpoint));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(endPointEmail));
				message.setSubject("SITE direct email test");
				
				Multipart multiPart = new MimeMultipart();  
				  
		        MimeBodyPart messageText = new MimeBodyPart();  
		        messageText.setContent("Dear User," + "\r\nAttached is the C-CDA document you have selected.", "text/plain");  
		        multiPart.addBodyPart(messageText);  
		        
		        multiPart.addBodyPart(ccdaAttachment);  
		  
		        message.setContent(multiPart);  
		        
		        Transport.send(message);
	 
		        directRecieveResults.getUploadResult().put("IsSuccess", "true");
		        directRecieveResults.getUploadResult().put("ErrorMessage", "Mail sent.");
				statisticsManager.addDirectReceive(domain, true, false, false);
				
			} catch (MessagingException e) {
				statisticsManager.addDirectReceive(domain, true, false, true);
				directRecieveResults.getUploadResult().put("IsSuccess", "false");
				directRecieveResults.getUploadResult().put("ErrorMessage", "Failed to send email due to eror: " + e.getMessage());
				
				e.printStackTrace();
				
			} 
			
			
		}
	}
	
	@RequestMapping(params = "javax.portlet.action=uploadCCDADirectEdgeReceive")
	public ModelAndView processUploadCCDADirectEdgeReceive(RenderRequest request, Model model)
			throws IOException {
		Map map = new HashMap();

		map.put("files", directRecieveResults.getFileJson());

		map.put("result", directRecieveResults.getUploadResult());

		return new ModelAndView("genericResultJsonView", map);
	}
	
	@ActionMapping(params = "javax.portlet.action=precannedCCDADirectEdgeReceive")
	public void precannedCCDADirectEdgeReceive(ActionRequest request, ActionResponse response) throws IOException, JSONException {
		
		String fromEmail = null;
		String fileName = null;
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String sampleCcdaDir = props.getProperty("sampleCcdaDir");
		String delimiter = "@";
		String hisptoemailaddress = props.getProperty("hisptoemailaddress");
		String smtphostname = props.getProperty("smtphostname");
		String smtpport = props.getProperty("smtpport");
		String enableSSL = props.getProperty("smtpenablessl");
		response.setRenderParameter("javax.portlet.action", "precannedCCDADirectEdgeReceive");
		String precannedfile = request.getParameter("precannedfilepath");
		String serverFilePath = sampleCcdaDir + "/" + precannedfile;
		fromEmail = request.getParameter("fromemail");
		String domain = fromEmail.split(delimiter)[1];
		
		directRecieveResults.setPrecannedResult(new JSONObject());

		try {
			final String smtpuser = props.getProperty("smtpusername");
			//final String smtppswrd = FileUtils.readFileToString(new File(props.getProperty("smtppswdPath")));
			//final String decyptedPass = new DesEncrypter(ENCRYPTEDKEY).decrypt(smtppswrd);
			MimeBodyPart ccdaAttachment = new MimeBodyPart();  
			
			DataSource ccdaFile = new FileDataSource(serverFilePath);  
	        ccdaAttachment.setDataHandler(new DataHandler(ccdaFile));  
	        ccdaAttachment.setFileName(ccdaFile.getName()); 
			Properties props = new Properties();
			
			props.put("mail.smtp.host", smtphostname);
			props.put("mail.pop3.host", smtphostname);
			if(enableSSL.toUpperCase().equals("TRUE")){
				props.put("mail.smtp.socketFactory.port", smtpport);
				props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				props.put("mail.pop3.socketFactory.port","110");
				props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", smtpport);
			props.put("mail.pop3.port", "110");
			props.put("mail.pop3.socketFactory.fallback", "false");
			
			
			Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(smtpuser, "providerpass");
					}
				});
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(hisptoemailaddress));
			message.setSubject("SITE direct email test");
			
			Multipart multiPart = new MimeMultipart();  
			  
	        MimeBodyPart messageText = new MimeBodyPart();  
	        messageText.setContent("Dear User," + "\r\nAttached is the C-CDA document you have selected.", "text/plain");  
	        multiPart.addBodyPart(messageText);  
	        
	        multiPart.addBodyPart(ccdaAttachment);  
	  
	        message.setContent(multiPart);  
	        
	        Transport.send(message);
 
	        directRecieveResults.getPrecannedResult().put("IsSuccess", "true");
	        directRecieveResults.getPrecannedResult().put("ErrorMessage", "Mail sent.");
			statisticsManager.addDirectReceive(domain, false, true, false);
			
			searchEmail(session, "sendbrianemail@gmail.com");
			
		} catch (MessagingException e) {
			statisticsManager.addDirectReceive(domain, false, true, true);
			directRecieveResults.getPrecannedResult().put("IsSuccess", "false");
			directRecieveResults.getPrecannedResult().put("ErrorMessage", "Failed to send email due to eror: " + e.getMessage());
			
			e.printStackTrace();
			
		} 
			
			
	}
	
	@RequestMapping(params = "javax.portlet.action=precannedCCDADirectEdgeReceive")
	public ModelAndView processPrecannedCCDADirectEdgeReceive(RenderRequest request, Model model)
			throws IOException {
		Map map = new HashMap();

		map.put("files", null);

		map.put("result", directRecieveResults.getPrecannedResult());

		return new ModelAndView("genericResultJsonView", map);
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
	
	private void searchEmail(Session session, final String keyword) {
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
//                        if (message.getSubject().contains(keyword)) {
//                            return true;
//                        }
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
 
            for (int i = 0; i < foundMessages.length; i++) {
                Message message = foundMessages[i];
                String subject = message.getSubject();
                System.out.println("DEBUG ---> Found message #" + i + ": " + subject);
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
    }
	

}
