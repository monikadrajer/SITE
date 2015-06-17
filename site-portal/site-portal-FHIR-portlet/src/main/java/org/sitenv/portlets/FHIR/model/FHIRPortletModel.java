package org.sitenv.portlets.FHIR.model;

public class FHIRPortletModel {
	
	private String resourceType;
	private String searchParam;
	private String searchParamValue;
	private String result;
	
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getSearchParam() {
		return searchParam;
	}
	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}
	public String getSearchParamValue() {
		return searchParamValue;
	}
	public void setSearchParamValue(String searchParamValue) {
		this.searchParamValue = searchParamValue;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
