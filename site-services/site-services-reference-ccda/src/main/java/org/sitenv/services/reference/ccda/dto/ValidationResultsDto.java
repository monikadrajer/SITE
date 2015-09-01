package org.sitenv.services.reference.ccda.dto;

import java.util.List;
import java.util.Map;

import org.sitenv.referenceccda.validator.RefCCDAValidationResult;

public class ValidationResultsDto {
	private ValidationResultsMetaData resultsMetaData;
	private Map<String, List<RefCCDAValidationResult>> ccdaValidationResults;

	public Map<String, List<RefCCDAValidationResult>> getCcdaValidationResults() {
		return ccdaValidationResults;
	}

	public void setCcdaValidationResults(Map<String, List<RefCCDAValidationResult>> ccdaValidationResults) {
		this.ccdaValidationResults = ccdaValidationResults;
	}

	public ValidationResultsMetaData getResultsMetaData() {
		return resultsMetaData;
	}

	public void setResultsMetaData(ValidationResultsMetaData resultsMetaData) {
		this.resultsMetaData = resultsMetaData;
	}

}
