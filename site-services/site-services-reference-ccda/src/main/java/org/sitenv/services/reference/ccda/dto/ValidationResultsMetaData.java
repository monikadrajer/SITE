package org.sitenv.services.reference.ccda.dto;

import org.sitenv.referenceccda.validator.enums.ErrorType;

public class ValidationResultsMetaData {
	private int ccdaIgInfoCount;
	private int ccdaIgWarningCount;
	private int ccdaIgErrorCount;
	private int ccdaVocabInfoCount;
	private int ccdaVocabWarningCount;
	private int ccdaVocabErrorCount;
	private int ccdaRefInfoCount;
	private int ccdaRefWarningCount;
	private int ccdaRefErrorCount;
	private String ccdaDocumentType;
	private boolean serviceError;
	private String serviceErrorMessage;

	public int getCcdaIgInfoCount() {
		return ccdaIgInfoCount;
	}

	public void setCcdaIgInfoCount(int ccdaIgInfoCount) {
		this.ccdaIgInfoCount = ccdaIgInfoCount;
	}

	public int getCcdaIgWarningCount() {
		return ccdaIgWarningCount;
	}

	public void setCcdaIgWarningCount(int ccdaIgWarningCount) {
		this.ccdaIgWarningCount = ccdaIgWarningCount;
	}

	public int getCcdaIgErrorCount() {
		return ccdaIgErrorCount;
	}

	public void setCcdaIgErrorCount(int ccdaIgErrorCount) {
		this.ccdaIgErrorCount = ccdaIgErrorCount;
	}

	public int getCcdaVocabInfoCount() {
		return ccdaVocabInfoCount;
	}

	public void setCcdaVocabInfoCount(int ccdaVocabInfoCount) {
		this.ccdaVocabInfoCount = ccdaVocabInfoCount;
	}

	public int getCcdaVocabWarningCount() {
		return ccdaVocabWarningCount;
	}

	public void setCcdaVocabWarningCount(int ccdaVocabWarningCount) {
		this.ccdaVocabWarningCount = ccdaVocabWarningCount;
	}

	public int getCcdaVocabErrorCount() {
		return ccdaVocabErrorCount;
	}

	public void setCcdaVocabErrorCount(int ccdaVocabErrorCount) {
		this.ccdaVocabErrorCount = ccdaVocabErrorCount;
	}

	public int getCcdaRefInfoCount() {
		return ccdaRefInfoCount;
	}

	public void setCcdaRefInfoCount(int ccdaRefInfoCount) {
		this.ccdaRefInfoCount = ccdaRefInfoCount;
	}

	public int getCcdaRefWarningCount() {
		return ccdaRefWarningCount;
	}

	public void setCcdaRefWarningCount(int ccdaRefWarningCount) {
		this.ccdaRefWarningCount = ccdaRefWarningCount;
	}

	public int getCcdaRefErrorCount() {
		return ccdaRefErrorCount;
	}

	public void setCcdaRefErrorCount(int ccdaRefErrorCount) {
		this.ccdaRefErrorCount = ccdaRefErrorCount;
	}

	public boolean hasCcdaIgInfo() {
		return ccdaIgInfoCount > 0;
	}

	public boolean hasCcdaIgWarnings() {
		return ccdaIgWarningCount > 0;
	}

	public boolean hasCcdaIgErrors() {
		return ccdaIgErrorCount > 0;
	}

	public boolean hasCcdaVocabInfo() {
		return ccdaVocabInfoCount > 0;
	}

	public boolean hasCcdaVocabWarnings() {
		return ccdaVocabWarningCount > 0;
	}

	public boolean hasCcdaVocabErrors() {
		return ccdaVocabErrorCount > 0;
	}

	public boolean hasCcdaRefInfo() {
		return ccdaRefInfoCount > 0;
	}

	public boolean hasCcdaRefWarnings() {
		return ccdaRefWarningCount > 0;
	}

	public boolean hasCcdaRefErrors() {
		return ccdaRefErrorCount > 0;
	}

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

	public void addCount(ErrorType errorType) {
		switch (errorType) {
		case CCDA_IG_CONFORMANCE_ERROR:
			ccdaIgErrorCount++;
			break;
		case CCDA_IG_CONFORMANCE_INFO:
			ccdaIgInfoCount++;
			break;
		case CCDA_IG_CONFORMANCE_WARN:
			ccdaIgWarningCount++;
			break;
		case CCDA_VOCAB_CONFORMANCE_ERROR:
			ccdaVocabErrorCount++;
			break;
		case CCDA_VOCAB_CONFORMANCE_INFO:
			ccdaVocabInfoCount++;
			break;
		case CCDA_VOCAB_CONFORMANCE_WARN:
			ccdaVocabWarningCount++;
			break;
		case REF_CCDA_ERROR:
			ccdaRefErrorCount++;
			break;
		case REF_CCDA_INFO:
			ccdaRefInfoCount++;
			break;
		case REF_CCDA_WARN:
			ccdaRefWarningCount++;
			break;
		default:
			break;
		}
	}
}
