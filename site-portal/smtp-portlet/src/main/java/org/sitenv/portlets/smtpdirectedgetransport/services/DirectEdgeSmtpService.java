package org.sitenv.portlets.smtpdirectedgetransport.services;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SearchTerm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class DirectEdgeSmtpService {
	public void sendEmail() {
	}
	
	public void getMessagesByFromAddress(Properties props, String fromAddress) {
		loadMailSessionProperties(props);
		searchEmail(createMailSession(props), fromAddress);
	}
	
	private void loadMailSessionProperties(Properties props) {
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
	
	private Session createMailSession(Properties props) {
		final String smtpuser = props.getProperty("smtpusername");
		return Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpuser, "providerpass");
			}
		});
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
						messageJsonObject.put("subject", message.getSubject());
						messageJsonObject.put("from", keyword);
						messageJSONArray.put(messageJsonObject);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
}
