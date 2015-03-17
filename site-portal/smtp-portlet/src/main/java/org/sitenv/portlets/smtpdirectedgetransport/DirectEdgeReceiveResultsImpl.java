package org.sitenv.portlets.smtpdirectedgetransport;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("DirectEdgeReceiveService")
@Scope(value="request",proxyMode= ScopedProxyMode.INTERFACES)
public class DirectEdgeReceiveResultsImpl implements DirectEdgeReceiveResults {

	
	private JSONArray fileJson = null;
	private JSONObject uploadResult = null;
	private JSONObject precannedResult = null;
	private JSONObject searchResult = null;
	
	public DirectEdgeReceiveResultsImpl(){}

	public JSONArray getFileJson() {
		return fileJson;
	}

	public void setFileJson(JSONArray fileJson) {
		this.fileJson = fileJson;
	}

	public JSONObject getUploadResult() {
		return uploadResult;
	}

	public void setUploadResult(JSONObject uploadResult) {
		this.uploadResult = uploadResult;
	}

	public JSONObject getPrecannedResult() {
		return precannedResult;
	}

	public void setPrecannedResult(JSONObject precannedResult) {
		this.precannedResult = precannedResult;
	}

	public JSONObject getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(JSONObject searchResult) {
		this.searchResult = searchResult;
	}

}
