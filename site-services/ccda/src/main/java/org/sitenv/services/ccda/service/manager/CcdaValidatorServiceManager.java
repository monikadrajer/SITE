package org.sitenv.services.ccda.service.manager;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringEscapeUtils;
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
import org.xml.sax.SAXException;

@Service
public class CcdaValidatorServiceManager {
	Logger logger = LogManager.getLogger(CcdaValidatorServiceManager.class.getName());
	@Autowired
	private CcdaValidationServiceFactory ccdaValidationServiceFactory;

	public String callCcda1_1ValidationServices(MultipartFile ccdaFileToValidate, String ccdaDocumentType) {
		JSONObject ccdaJSON = null;
		JSONObject vocabularyJSON = null;
		JSONObject dataQualityJSON = null;
		try {
			buildDocumentToCheckForWellFormedXml(ccdaFileToValidate);

			if (ccdaDocumentType.equals(CcdaType.NonSpecificCCDA.toString())) {
				ccdaJSON = ccdaValidationServiceFactory.getCcdaValidatorService(
						CcdaValidatorTypes.NonSpecificCCDADocTypeCCDA1_1.toString()).callValidationService(ccdaFileToValidate,
						ccdaDocumentType);
			} else {
				ccdaJSON = ccdaValidationServiceFactory.getCcdaValidatorService(
						CcdaValidatorTypes.SpecificCCDADocTypeCCDA1_1.toString()).callValidationService(ccdaFileToValidate,
						ccdaDocumentType);
			}
			vocabularyJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.Vocabulary.toString())
					.callValidationService(ccdaFileToValidate, ccdaDocumentType);
			dataQualityJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.DataQuality.toString())
					.callValidationService(ccdaFileToValidate, ccdaDocumentType);
			return buildResponse(ccdaJSON, vocabularyJSON, dataQualityJSON).toString();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			return "{ \"ccdaResults\" : {\"error\":" + "\"" + StringEscapeUtils.escapeHtml4(e.getMessage()) + "\"" + "}}";
		}
	}

	public String callCcda2_0ValidationServices(MultipartFile ccdaFileToValidate, String cCDAR2_0_type_val,
			String referenceFileNameUsed) {
		JSONObject ccdaJSON = null;
		JSONObject vocabularyJSON = null;
		JSONObject dataQualityJSON = null;
		try {
			buildDocumentToCheckForWellFormedXml(ccdaFileToValidate);

			ccdaJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.CCDA2_0.toString())
					.callValidationService(ccdaFileToValidate, null);
			vocabularyJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.Vocabulary.toString())
					.callValidationService(ccdaFileToValidate, null);
			dataQualityJSON = ccdaValidationServiceFactory.getCcdaValidatorService(CcdaValidatorTypes.DataQuality.toString())
					.callValidationService(ccdaFileToValidate, null);
			return buildResponse(ccdaJSON, vocabularyJSON, dataQualityJSON).toString();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			return "{ \"ccdaResults\" : {\"error\":" + "\"" + StringEscapeUtils.escapeXml(e.getMessage()) + "\"" + "}}";
		}
	}

	private void buildDocumentToCheckForWellFormedXml(MultipartFile ccdaFileToValidate) throws SAXException, IOException,
			ParserConfigurationException {
		DocumentBuilder builder;
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		builder = domFactory.newDocumentBuilder();
		builder.parse(ccdaFileToValidate.getInputStream());
	}

	private JSONObject buildResponse(JSONObject ccdaJSON, JSONObject extendedCcdaJSON, JSONObject dataQualityCcdaJSON) {
		JSONObject json = new JSONObject();
		try {
			if (!json.has("error")) {
				json.put("ccdaResults", ccdaJSON);
				json.put("ccdaExtendedResults", extendedCcdaJSON);
				json.put("ccdaDataQualityResults", dataQualityCcdaJSON);
			}

		} catch (JSONException e) {
			logger.error("Error while accessing CCDA service: ", e);
			try {
				json = new JSONObject("{ \"error\" : {\"message\":" + "\"" + e.getMessage() + "\"" + "}}");
			} catch (JSONException e1) {
				logger.error("Error while creating error JSON output: ", e1);
			}
		}
		return json;
	}
}
