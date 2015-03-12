package org.sitenv.portlets.xdrvalidator.models;

import org.json.JSONArray;
import org.json.JSONObject;

public interface XdrReceiveResults {

	public JSONArray getFileJson();
	public void setFileJson(JSONArray fileJson);
	public JSONObject getJsonResponseBody();
	public void setJsonResponseBody(JSONObject jsonResponseBody);
	
}
