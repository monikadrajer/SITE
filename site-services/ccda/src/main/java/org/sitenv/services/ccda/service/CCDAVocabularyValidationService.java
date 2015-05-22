package org.sitenv.services.ccda.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component(value="Vocabulary")
public class CCDAVocabularyValidationService extends BaseCCDAValidationService {
	@Value( "${ExtendedCCDAValidationServiceURL}" )
	private String extendedCCDAValidationServiceURL;
	
	@Override
	public String getServiceLocation() {
		return extendedCCDAValidationServiceURL;
	}

	@Override
	protected void setValidatorId(String validatorId) {
		this.validatorId = "";
	}
	
	@Override
	protected JSONObject handleCCDAValidationResponse(HttpResponse relayResponse) throws JSONException, ClientProtocolException, IOException {
		ResponseHandler<String> handler = new BasicResponseHandler();
		int code = relayResponse.getStatusLine().getStatusCode();
		JSONObject jsonbody = null;
		
		if(code != HttpStatus.SC_OK){
			jsonbody = handleBadServiceResponse(relayResponse, code);
		}else{
			String body = handler.handleResponse(relayResponse);
			jsonbody = new JSONObject(body);
			JSONObject report = new JSONObject();
			
			boolean hasErrors = jsonbody.getBoolean("errors");
			boolean hasWarnings = jsonbody.getBoolean("warnings");
			boolean hasInfo = jsonbody.getBoolean("information");
			
			jsonbody.remove("errors");
			jsonbody.remove("warnings");
			jsonbody.remove("information");
			
			report.put("hasErrors", hasErrors);
			report.put("hasWarnings", hasWarnings);
			report.put("hasInfo", hasInfo);
			jsonbody.put("report", report);
		}
		return jsonbody;
	}
}
