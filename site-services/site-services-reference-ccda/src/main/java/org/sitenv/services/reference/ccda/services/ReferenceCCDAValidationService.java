package org.sitenv.services.reference.ccda.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.sitenv.referenceccda.validator.RefCCDAValidationResult;
import org.sitenv.referenceccda.validator.ReferenceCCDAValidator;
import org.sitenv.services.reference.ccda.dto.ValidationResultsDto;
import org.sitenv.services.reference.ccda.dto.ValidationResultsMetaData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReferenceCCDAValidationService {

	public ValidationResultsDto validateCCDA(String validationObjective, String referenceFileName, MultipartFile ccdaFile) {
		ValidationResultsDto resultsDto = new ValidationResultsDto();
		Map<String, List<RefCCDAValidationResult>> validationResults = new HashMap<>();
		String ccdaFileContents;
		InputStream fileIs = null;
		try {
			ValidationResultsMetaData resultsMetaData = new ValidationResultsMetaData();
			fileIs = ccdaFile.getInputStream();
			ccdaFileContents = IOUtils.toString(ccdaFile.getInputStream());
			List<RefCCDAValidationResult> validatorResults = ReferenceCCDAValidator.validateCCDAWithReferenceFileName(
					validationObjective, referenceFileName, ccdaFileContents);
			for (RefCCDAValidationResult result : validatorResults) {
				if (validationResults.containsKey(result.getErrorType().getErrorTypePrettyName())) {
					validationResults.get(result.getErrorType().getErrorTypePrettyName()).add(result);
					resultsMetaData.addCount(result.getErrorType());
				} else {
					ArrayList<RefCCDAValidationResult> resultList = new ArrayList<>();
					validationResults.put(result.getErrorType().getErrorTypePrettyName(), resultList);
					resultsMetaData.addCount(result.getErrorType());
				}
			}
			resultsDto.setResultsMetaData(resultsMetaData);
			resultsDto.setCcdaValidationResults(validationResults);
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
		return resultsDto;
	}
}
