package org.sitenv.services.reference.ccda.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.sitenv.referenceccda.validator.RefCCDAValidationResult;
import org.sitenv.referenceccda.validator.ReferenceCCDAValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReferenceCCDAValidationService {

	public ArrayList<RefCCDAValidationResult> validateCCDA(String validationObjective, String referenceFileName,
			MultipartFile ccdaFile) {
		String ccdaFileContents;
		InputStream fileIs = null;
		ArrayList<RefCCDAValidationResult> validationResults = new ArrayList<RefCCDAValidationResult>();
		try {
			fileIs = ccdaFile.getInputStream();
			ccdaFileContents = IOUtils.toString(ccdaFile.getInputStream());
			validationResults = ReferenceCCDAValidator.validateCCDAWithReferenceFileName(validationObjective, referenceFileName,
					ccdaFileContents);
		} catch (IOException e) {
			throw new RuntimeException("Error getting CCDA contents from provided file", e);
		} finally {
			if (fileIs != null) {
				try {
					fileIs.close();
				} catch (IOException e) {
					throw new RuntimeException("Error closing CCDA file input stream", e);
				}
			}
		}
		return validationResults;
	}
}
