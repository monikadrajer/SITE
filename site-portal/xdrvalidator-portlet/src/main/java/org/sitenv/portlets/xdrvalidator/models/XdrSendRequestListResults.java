package org.sitenv.portlets.xdrvalidator.models;

import java.util.List;

import org.json.JSONObject;

public interface XdrSendRequestListResults {

	public String getLookupCode();
	public void setLookupCode(String lookupCode);
	public List<String> getRequestTimestamps();
	public void setRequestTimestamps(List<String> requestTimestamps);
	
}
