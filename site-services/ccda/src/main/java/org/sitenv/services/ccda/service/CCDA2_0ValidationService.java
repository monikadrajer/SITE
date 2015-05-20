package org.sitenv.services.ccda.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="CCDA2_0")
public class CCDA2_0ValidationService extends BaseCCDAValidationService {
	Logger logger = LogManager.getLogger(CCDA2_0ValidationService.class.getName());	 
	@Value( "${CCDAValidationServiceURL}" )
	private String ccdaValidationServiceURL;

	@Override
	public String getServiceLocation() {
		return ccdaValidationServiceURL;
	}

	@Override
	protected void setValidatorId(String validatorId) {
		this.validatorId = "2.0";
	}
}

	