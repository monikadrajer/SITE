package org.sitenv.portlets.smtpdirectedgetransport.models;



import java.util.Date;

import javax.activation.DataSource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class SimpleEmailMessageAttributes {
	private String to;
	private String from;
	private String messageSubject;
	private String messageBody;
	private String attachmentName;
	private DataSource attachment;
	private String attachmentContentType;
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
	public DataSource getAttachment() {
		return attachment;
	}
	public void setAttachment(DataSource attachment) {
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
	public String getAttachmentContentType() {
		return attachmentContentType;
	}
	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	
	@Override
	public String toString(){
	    return ReflectionToStringBuilder.toString(this);
	}
}
