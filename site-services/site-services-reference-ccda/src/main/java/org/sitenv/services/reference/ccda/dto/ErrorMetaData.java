package org.sitenv.services.reference.ccda.dto;

public class ErrorMetaData {
	private String errorType;
	private int errorCount;

	public ErrorMetaData(String errorType, int errorCount) {
		this.errorType = errorType;
		this.errorCount = errorCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (errorType == null ? 0 : errorType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ErrorMetaData other = (ErrorMetaData) obj;
		if (errorType == null) {
			if (other.errorType != null) {
				return false;
			}
		} else if (!errorType.equals(other.errorType)) {
			return false;
		}
		return true;
	}

}
