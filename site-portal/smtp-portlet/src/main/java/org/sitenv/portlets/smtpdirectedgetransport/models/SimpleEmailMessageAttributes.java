package org.sitenv.portlets.smtpdirectedgetransport.models;



import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class SimpleEmailMessageAttributes {
	private String to;
	private String from;
	private String messageSubject;
	private String messageBody;
	private String attachmentName;
	private byte[] attachment;
	private Date recievedDate;
	private Date sentDate;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMessageSubject() {
		return messageSubject;
	}
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	public Date getRecievedDate() {
		return recievedDate;
	}
	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public boolean hasAttachment() {
		return !attachmentName.isEmpty();
	}
	
	@Override
	public String toString(){
	    return ReflectionToStringBuilder.toString(this);
	}
}
