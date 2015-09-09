package org.sitenv.services.reference.ccda.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.sitenv.referenceccda.validator.enums.ErrorType;

public class ValidationResultsMetaData {
	private String ccdaDocumentType;
	private boolean serviceError;
	private String serviceErrorMessage;
	private final Map<String, AtomicInteger> errorCounts = new LinkedHashMap<String, AtomicInteger>();
	private List<ErrorMetaData> errorMetaData;

	public String getCcdaDocumentType() {
		return ccdaDocumentType;
	}

	public void setCcdaDocumentType(String ccdaDocumentType) {
		this.ccdaDocumentType = ccdaDocumentType;
	}

	public boolean isServiceError() {
		return serviceError;
	}

	public void setServiceError(boolean serviceError) {
		this.serviceError = serviceError;
	}

	public String getServiceErrorMessage() {
		return serviceErrorMessage;
	}

	public void setServiceErrorMessage(String serviceErrorMessage) {
		this.serviceErrorMessage = serviceErrorMessage;
	}

	public List<ErrorMetaData> getErrorMetaData() {
		errorMetaData = new ArrayList<ErrorMetaData>();
		for (Map.Entry<String, AtomicInteger> entry : errorCounts.entrySet()) {
			errorMetaData.add(new ErrorMetaData(entry.getKey(), entry.getValue().intValue()));
		}
		return errorMetaData;
	}

	public void addCount(ErrorType errorType) {
		if (errorCounts.containsKey(errorType.getErrorTypePrettyName())) {
			errorCounts.get(errorType.getErrorTypePrettyName()).addAndGet(1);
		} else {
			errorCounts.put(errorType.getErrorTypePrettyName(), new AtomicInteger(1));
		}
	}
}
