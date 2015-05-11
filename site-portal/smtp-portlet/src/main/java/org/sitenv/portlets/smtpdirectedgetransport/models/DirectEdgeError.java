package org.sitenv.portlets.smtpdirectedgetransport.models;

public class DirectEdgeError extends Exception {
	private static final long serialVersionUID = 1L;
	private String errMsg;
 
	public DirectEdgeError(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
