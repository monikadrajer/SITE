package org.sitenv.portlets.FHIR.model;

import java.io.Serializable;

public class TestCaseResultWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
    private String status;
    private String request;
	private String response;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
    public String getRequest() {
		return request;
	}
	
	public void setRequest(String request) {
		this.request = request;
	}
	
	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
}
