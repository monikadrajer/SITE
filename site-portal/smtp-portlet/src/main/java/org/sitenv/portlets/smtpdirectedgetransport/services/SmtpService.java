package org.sitenv.portlets.smtpdirectedgetransport.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
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

import org.sitenv.portlets.smtpdirectedgetransport.models.SimpleEmailMessageAttachmentAttributes;
import org.sitenv.portlets.smtpdirectedgetransport.models.SimpleEmailMessageAttributes;

public abstract class SmtpService {

	public Message createMessage(Properties props, SimpleEmailMessageAttributes emailMessageAttributes) throws MessagingException,
	AddressException {
		Message message = new MimeMessage(getEmailSession(props));
		message.setSubject(emailMessageAttributes.getMessageSubject());
		message.setFrom(new InternetAddress(emailMessageAttributes.getFrom()));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailMessageAttributes.getTo()));
		Multipart multiPart = new MimeMultipart();  
		MimeBodyPart messageText = new MimeBodyPart();  
		messageText.setContent(emailMessageAttributes.getMessageBody(), "text/plain");  
		multiPart.addBodyPart(messageText);
		if(emailMessageAttributes.hasAttachment()) {
			for(SimpleEmailMessageAttachmentAttributes attachmentAttributes : emailMessageAttributes.getAttachments()) {
				MimeBodyPart ccdaAttachment = createMessageAttachment(attachmentAttributes.getAttachmentName(), attachmentAttributes.getAttachment(), attachmentAttributes.getAttachmentContentType());    
				multiPart.addBodyPart(ccdaAttachment);
			}
		}
		message.setContent(multiPart);
		return message;
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

	protected MimeBodyPart createMessageAttachment(String fileName, DataSource attachment, String contentType) throws MessagingException {
		MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		attachmentBodyPart.setDataHandler(new DataHandler(attachment));  
		attachmentBodyPart.setFileName(fileName);
		return attachmentBodyPart;
	}

	public void sendMessage(Message message) throws MessagingException {
		Transport.send(message);
	}

	public List<SimpleEmailMessageAttributes> searchEmail(Properties props, final String keyword) throws MessagingException {
		List<SimpleEmailMessageAttributes> searchResults = new ArrayList<SimpleEmailMessageAttributes>();	
		Store store = getEmailSession(props).getStore("pop3");
		store.connect();

		Folder folderInbox = openInbox(store);
		SearchTerm searchCondition = createSearchCriterion(keyword);
		Message[] foundMessages = folderInbox.search(searchCondition);
		if(foundMessages.length > 0) {
			for (int i = 0; i < foundMessages.length; i++) {
				Message message = foundMessages[i];
				SimpleEmailMessageAttributes messageAttributes = new SimpleEmailMessageAttributes();
				List<SimpleEmailMessageAttachmentAttributes> attachments = new ArrayList<SimpleEmailMessageAttachmentAttributes>();
				messageAttributes.setMessageSubject(message.getSubject());
				messageAttributes.setFrom(keyword);
				messageAttributes.setRecievedDate(message.getReceivedDate());
				messageAttributes.setSentDate(message.getSentDate());
				Multipart mp;
				try {
					mp = (Multipart) message.getContent();
					for (int j = 0; j < mp.getCount(); j++) {
						BodyPart b = mp.getBodyPart(j);
						if(b.getDisposition() == null || b.getDisposition().equalsIgnoreCase(Part.INLINE)) {
							messageAttributes.setMessageBody(b.getContent().toString());
						}else if(b.getDescription() == null || b.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
							SimpleEmailMessageAttachmentAttributes attachmentAttributes = new SimpleEmailMessageAttachmentAttributes();
							attachmentAttributes.setAttachmentName(b.getFileName());
							attachmentAttributes.setAttachment(b.getDataHandler().getDataSource());
							attachmentAttributes.setAttachmentContentType(b.getContentType());
							attachments.add(attachmentAttributes);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				messageAttributes.setAttachments(attachments);
				searchResults.add(messageAttributes);
			}
		}
		folderInbox.close(false);
		store.close();
		Collections.sort(searchResults);
		Collections.reverse(searchResults);
		return searchResults;
	}

	private SearchTerm createSearchCriterion(final String keyword) {
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
		return searchCondition;
	}

	private Folder openInbox(Store store) throws MessagingException {
		Folder folderInbox = store.getFolder("INBOX");
		folderInbox.open(Folder.READ_ONLY);
		return folderInbox;
	}

}
