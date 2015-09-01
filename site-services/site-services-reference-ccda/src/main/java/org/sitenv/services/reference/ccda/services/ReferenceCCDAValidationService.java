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
		ValidationResultsMetaData resultsMetaData = new ValidationResultsMetaData();
		Map<String, List<RefCCDAValidationResult>> processedResults = new HashMap<>();
		List<RefCCDAValidationResult> validatorResults = getValidationResults(validationObjective, referenceFileName, ccdaFile);
		processValidationResults(processedResults, resultsMetaData, validatorResults);
		resultsDto.setResultsMetaData(resultsMetaData);
		resultsDto.setCcdaValidationResults(processedResults);
		return resultsDto;
	}

	private List<RefCCDAValidationResult> getValidationResults(String validationObjective, String referenceFileName,
			MultipartFile ccdaFile) {
		List<RefCCDAValidationResult> validatorResults = new ArrayList<RefCCDAValidationResult>();
		String ccdaFileContents;
		InputStream fileIs = null;
		try {
			fileIs = ccdaFile.getInputStream();
			ccdaFileContents = IOUtils.toString(ccdaFile.getInputStream());
			validatorResults = ReferenceCCDAValidator.validateCCDAWithReferenceFileName(validationObjective, referenceFileName,
					ccdaFileContents);
		} catch (IOException e) {
			throw new RuntimeException("Error getting CCDA contents from provided file", e);
		} finally {
			closeFileInputStream(fileIs);
		}
		return validatorResults;
	}

	private void processValidationResults(Map<String, List<RefCCDAValidationResult>> processedResults,
			ValidationResultsMetaData resultsMetaData, List<RefCCDAValidationResult> validatorResults) {
		for (RefCCDAValidationResult result : validatorResults) {
			addResultToErrorTypeMap(processedResults, resultsMetaData, result);
			resultsMetaData.addCount(result.getErrorType());
		}
	}

	private void addResultToErrorTypeMap(Map<String, List<RefCCDAValidationResult>> validationResults,
			ValidationResultsMetaData resultsMetaData, RefCCDAValidationResult result) {
		if (validationResults.containsKey(result.getErrorType().getErrorTypePrettyName())) {
			validationResults.get(result.getErrorType().getErrorTypePrettyName()).add(result);
		} else {
			addNewErrorTypeListToMap(validationResults, result);
		}
	}

	private void addNewErrorTypeListToMap(Map<String, List<RefCCDAValidationResult>> validationResults,
			RefCCDAValidationResult result) {
		ArrayList<RefCCDAValidationResult> resultList = new ArrayList<>();
		validationResults.put(result.getErrorType().getErrorTypePrettyName(), resultList);
	}

	private void closeFileInputStream(InputStream fileIs) {
		if (fileIs != null) {
			try {
				fileIs.close();
			} catch (IOException e) {
				throw new RuntimeException("Error closing CCDA file input stream", e);
			}
		}
	}
}
