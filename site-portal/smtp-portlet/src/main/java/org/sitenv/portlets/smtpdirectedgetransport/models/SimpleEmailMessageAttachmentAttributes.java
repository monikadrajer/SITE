package org.sitenv.portlets.smtpdirectedgetransport.models;

import javax.activation.DataSource;

public class SimpleEmailMessageAttachmentAttributes {
	private String attachmentName;
	private DataSource attachment;
	private String attachmentContentType;
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
	public String getAttachmentContentType() {
		return attachmentContentType;
	}
	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	
	
}
