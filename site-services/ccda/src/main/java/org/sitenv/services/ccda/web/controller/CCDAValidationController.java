package org.sitenv.services.ccda.web.controller;

import java.io.IOException;

import org.sitenv.services.ccda.service.manager.CcdaValidatorServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "", produces = MediaType.TEXT_XML_VALUE)
public class CCDAValidationController {
	@Autowired
	private CcdaValidatorServiceManager ccdaValidatorServiceManager;

	@RequestMapping(value = "/r1.1/", headers = "content-type=multipart/*", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String validater1_1(@RequestParam(value = "type_val", required = false) String type_val,
			@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		return ccdaValidatorServiceManager.callCcda1_1ValidationServices(file, type_val);
	}

	@RequestMapping(value = "/r2.0/", headers = "content-type=multipart/*", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String validater2_0(@RequestParam(value = "CCDAR2_0_type_val", required = false) String CCDAR2_0_type_val,
			@RequestParam(value = "referenceFileUsed", required = false) String referenceFileNameUsed,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		return ccdaValidatorServiceManager.callCcda2_0ValidationServices(file, CCDAR2_0_type_val, referenceFileNameUsed);
	}

}
