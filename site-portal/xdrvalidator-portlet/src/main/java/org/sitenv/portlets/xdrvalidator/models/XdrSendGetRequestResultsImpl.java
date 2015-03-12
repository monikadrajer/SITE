package org.sitenv.portlets.xdrvalidator.models;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("xdrSendGetRequestResultsService")
@Scope(value="request",proxyMode= ScopedProxyMode.INTERFACES)
public class XdrSendGetRequestResultsImpl implements XdrSendGetRequestResults {
	
	private String lookupCode;
	private String timestamp;
	private String requestContent;
	private String responseContent;
	
	public String getLookupCode() {
		return lookupCode;
	}
	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getRequestContent() {
		return requestContent;
	}
	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}
	public String getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
	
}
