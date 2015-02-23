package org.sitenv.portlets.xdrvalidator.models;

import java.util.List;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("xdrSendRequestListResultsService")
@Scope(value="request",proxyMode= ScopedProxyMode.INTERFACES)
public class XdrSendRequestListResultsImpl implements XdrSendRequestListResults {
	
	private String lookupCode;
	private List<String> requestTimestamps;
	
	public String getLookupCode() {
		return lookupCode;
	}
	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}
	public List<String> getRequestTimestamps() {
		return requestTimestamps;
	}
	public void setRequestTimestamps(List<String> requestTimestamps) {
		this.requestTimestamps = requestTimestamps;
	}
	
	
}
