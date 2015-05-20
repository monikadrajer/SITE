package org.sitenv.services.ccda.service.manager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.sitenv.common.utilities.enums.CcdaType;
import org.sitenv.services.ccda.enums.CcdaValidatorTypes;
import org.sitenv.services.ccda.service.factory.CcdaValidationServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CcdaValidatorServiceManager {
	Logger logger = LogManager.getLogger(CcdaValidatorServiceManager.class.getName());
	@Autowired
	private CcdaValidationServiceFactory ccdaValidationServiceFactory;
	
	public String callCcda1_1ValidationServices(MultipartFile ccdaFileToValidate, String ccdaDocumentType) {
		JSONObject ccdaJSON = null;
		JSONObject vocabularyJSON = null;
		JSONObject dataQualityJSON = null;
		if (ccdaDocumentType.equals(CcdaType.NonSpecificCCDA.toString())) {
			ccdaJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.NonSpecificCCDADocTypeCCDA1_1.toString()).callValidationService(ccdaFileToValidate, ccdaDocumentType);
		} else {
			ccdaJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.SpecificCCDADocTypeCCDA1_1.toString()).callValidationService(ccdaFileToValidate, ccdaDocumentType);
		}
		vocabularyJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.Vocabulary.toString()).callValidationService(ccdaFileToValidate, ccdaDocumentType);		
		dataQualityJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.DataQuality.toString()).callValidationService(ccdaFileToValidate, ccdaDocumentType);
		return buildResponse(ccdaJSON, vocabularyJSON, dataQualityJSON).toString();
	}
	
	public String callCcda2_0ValidationServices(MultipartFile ccdaFileToValidate) {
		JSONObject ccdaJSON = null;
		JSONObject vocabularyJSON = null;
		JSONObject dataQualityJSON = null;
		ccdaJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.CCDA2_0.toString()).callValidationService(ccdaFileToValidate, null);
		vocabularyJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.Vocabulary.toString()).callValidationService(ccdaFileToValidate, null);		
		dataQualityJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.DataQuality.toString()).callValidationService(ccdaFileToValidate, null);
		return buildResponse(ccdaJSON, vocabularyJSON, dataQualityJSON).toString();
	}
	
	private JSONObject buildResponse(JSONObject ccdaJSON, JSONObject extendedCcdaJSON, JSONObject dataQualityCcdaJSON) {
		JSONObject json = new JSONObject();
		try {
			if (!json.has("error")){
				json.put("ccdaResults", ccdaJSON);
				json.put("ccdaExtendedResults", extendedCcdaJSON);
				json.put("ccdaDataQualityResults", dataQualityCcdaJSON);
			}

		} catch (JSONException e) {
			logger.error("Error while accessing CCDA service: ",  e);
			try {
				json = new JSONObject("{ \"error\" : {\"message\":"+"\""+e.getMessage()+"\""+"}}");
			} catch (JSONException e1) {
				logger.error("Error while creating error JSON output: ",  e1);
			}
		}
		return json;
	}
}
