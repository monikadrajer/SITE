package org.sitenv.portlets.xdrvalidator.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("xdrReceiveResultsService")
@Scope(value="request",proxyMode= ScopedProxyMode.INTERFACES)
public class XdrReceiveResultsImpl implements XdrReceiveResults {

	private JSONArray fileJson;
	private JSONObject jsonResponseBody;
	
	public JSONArray getFileJson() {
		return fileJson;
	}
	public void setFileJson(JSONArray fileJson) {
		this.fileJson = fileJson;
	}
	public JSONObject getJsonResponseBody() {
		return jsonResponseBody;
	}
	public void setJsonResponseBody(JSONObject jsonResponseBody) {
		this.jsonResponseBody = jsonResponseBody;
	}
	
	
	
}
