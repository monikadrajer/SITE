package org.sitenv.portlets.smtpdirectedgetransport;

import org.json.JSONArray;
import org.json.JSONObject;

public interface DirectEdgeReceiveResults {

	public JSONArray getFileJson();
	public void setFileJson(JSONArray fileJson);
	public JSONObject getUploadResult();
	public void setUploadResult(JSONObject uploadResult);
	public JSONObject getPrecannedResult();
	public void setPrecannedResult(JSONObject precannedResult);
	public JSONArray getSearchResult();
	public void setSearchResult(JSONArray searchResult);
}
