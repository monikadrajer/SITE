package org.sitenv.portlets.smtpdirectedgetransport.models;



import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class SimpleEmailMessageAttributes {
	private String to;
	private String from;
	private String messageSubject;
	private String messageBody;
	private Date recievedDate;
	private Date sentDate;
	private List<SimpleEmailMessageAttachmentAttributes>  attachments;
	
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
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
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
		return !attachments.isEmpty();
	}
	public List<SimpleEmailMessageAttachmentAttributes> getAttachments() {
		return attachments;
	}
	public void setAttachments(
			List<SimpleEmailMessageAttachmentAttributes> attachments) {
		this.attachments = attachments;
	}
	@Override
	public String toString(){
	    return ReflectionToStringBuilder.toString(this);
	}
}
