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
	
	
	
	/*protected void logSuccessOrFailure(JSONObject json, Date requestStart, Date requestFinish){
		String logMessage = "";
		try {
			
			JSONObject error = json.getJSONObject("error");
			String message = error.getString("message");
			logMessage = "[Failure] RequestTime: "+requestStart.toString()+" ResponseTime:"+requestFinish+" Message:"+message;
			
		} catch (JSONException e) {
			logMessage = "[Success] RequestTime: "+requestStart.toString()+" ResponseTime:"+requestFinish;
		}
		logger.info(logMessage);
	}*/
	
	
	/*private JSONObject getError(JSONObject json){
		JSONObject error = new JSONObject();
		
		if (json.has("error")){
			try {
				error.put("error", json.getJSONObject("error"));
			} catch (JSONException e) {
				logger.error("Error while creating error JSON output: ", e);
			}
		}
		return error;
	}*/
	
	
	/*protected JSONObject writeCcdaAndVocabStatistics(JSONObject ccdaJSON,
			JSONObject vocabCcdaJSON, 
			String validationType) throws JSONException{
		
		JSONObject errorJson = null;
		
		
		if ((ccdaJSON == null) || (vocabCcdaJSON == null)){
			recordStatistics(validationType, false, false, false, true);
		
		} else if (getError(ccdaJSON) != null){
			recordStatistics(validationType, false, false, false, true);
			errorJson = getError(ccdaJSON);
		
		} else if (getError(vocabCcdaJSON) != null){
			recordStatistics(validationType, false, false, false, true);
			errorJson = getError(vocabCcdaJSON);
		} else {
			
			JSONObject ccdaReport = ccdaJSON.getJSONObject("report");
			JSONObject ccdaExtendedReport = vocabCcdaJSON.getJSONObject("report");
			
			boolean hasErrors = (ccdaReport.getBoolean("hasErrors") || 
					ccdaExtendedReport.getBoolean("hasErrors"));
			
			boolean hasWarnings = (ccdaReport.getBoolean("hasWarnings") || 
					ccdaExtendedReport.getBoolean("hasWarnings"));
			
			boolean hasInfo = (ccdaReport.getBoolean("hasInfo") || 
					ccdaExtendedReport.getBoolean("hasInfo"));
			
			recordStatistics(validationType, hasErrors, hasWarnings, hasInfo, false);
		}
		return errorJson;
	}*/

}
