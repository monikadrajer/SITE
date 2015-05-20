package org.sitenv.services.ccda.service;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface ValidationService{
	public JSONObject callValidationService(MultipartFile FileToValidate, String DocumentType);
}
