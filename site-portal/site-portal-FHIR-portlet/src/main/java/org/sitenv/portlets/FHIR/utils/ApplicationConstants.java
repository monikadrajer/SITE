package org.sitenv.portlets.FHIR.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApplicationConstants {
	
	public static final String PATIENT_RESOURCE = "Patient";
	public static final String OBSERVATION_RESOURCE = "Observation";
	public static final String FHIR_BASEURI = "http://localhost:8082/FHIRServer/fhir/";
	public static final String FHIR_FORMAT = "&_format=application/json";
	public static final String SELECT_DEFAULT_VALUE = "-1";
	public static final String SERVER_NA_ERROR_MESSAGE = "{error : The Requested Server is not available or something went wrong with the server}";
	public static final String TESTCASE_PROP_FILE_NAME ="FHIRTestCase.properties";
	
	
	public static final Map<String, String> FHIR_TESTCASE_MAP = new HashMap<String , String>();
	public static final List<String> patientAllTestCases = Arrays.asList("patientById","patientByLanguage","patientByIdentifier","patientByActive",	
													 "patientByName","patientByFamily","patientByGiven","patientByPhonetic",	
													 "patientByTelecom","patientByAddress","patientByGender","patientByBirthdate",	
													 "patientByOrganization","patientByCareprovider","patientByAnimal-species",
													 "patientByAnimal-breed","patientByLink","patientByDeceased","patientByDeathdate");
	
	public static final List<String> observationAllTestCases = Arrays.asList("observationByCode","observationByValue-quantity","observationByValue-concept",               
																"observationByValue-date","observationByValue-string","observationByDate","observationByStatus",                      
																"observationByReliability","observationBySubject","observationByPerformer","observationBySpecimen",                    
																"observationByRelated-type","observationByRelated-target","observationByEncounter","observationByData-absent-reason",          
																"observationByPatient","observationByIdentifier","observationByDevice","observationByCode-value-quantity",         
																"observationByCode-value-concept","observationByCode-value-date","observationByCode-value-string",			 
																"observationByRelated-target-related-type" );
	
	public static final List<String> medicationAllTestCases = Arrays.asList("medicationByCode","medicationByValue","medicationByManufacture","medicationByForm",		
																"medicationByIngredient","medicationByContainer","medicationByContent");
	
	public static final List<String> immunizationAllTestCases = Arrays.asList("immunizationByDate","immunizationByDose-sequence","immunizationByIdentifier",
																	"immunizationByLocation","immunizationByLot-number","immunizationByManufacturer",	
																	"immunizationByPerformer","immunizationByReaction","immunizationByReaction-date",	
																	"immunizationByReason","immunizationByNotgiven","immunizationByRequester","immunizationByPatient",		
																	"immunizationByVaccine-type","immunizationBySubject");
	
	public static final List<String> allergyIntoleranceAllTestCases = Arrays.asList("allergyIntoleranceBySubstance","allergyIntoleranceByStatus","allergyIntoleranceByCriticality",		
																		"allergyIntoleranceByType","allergyIntoleranceByCategory","allergyIntoleranceByLast-date",		
																		"allergyIntoleranceByManifestation","allergyIntoleranceByOnset","allergyIntoleranceByDuration",		
																		"allergyIntoleranceBySeverity","allergyIntoleranceByRoute","allergyIntoleranceByIdentifier",		
																		"allergyIntoleranceByDate","allergyIntoleranceByRecorder","allergyIntoleranceByPatient","allergyIntoleranceByReporter");
	
	public static final List<String> conditionAllTestCases = Arrays.asList("conditionByCode","conditionByClinicalstatus","conditionBySeverity","conditionByCategory",		
																		"conditionByOnset","conditionByOnset-info","conditionByEncounter","conditionByAsserter",		
																		"conditionByDate-asserted","conditionByEvidence","conditionByStage","conditionByPatient",		
																		"conditionBySubject","conditionByDueto-code","conditionByDueto-item","conditionByFollowing-code",
																		"conditionByFollowing-item");
	
	public static final List<String> encounterAllTestCases = Arrays.asList("encounterByIdentifier","encounterByStatus","encounterByDate","encounterByPatient",				
																	"encounterByFulfills","encounterByLength","encounterByReason","encounterByIndication","encounterByLocation",				
																	"encounterByLocation-period","encounterByType","encounterBySpecial-arrangement","encounterByPart-of",				
																	"encounterByParticipant","encounterByParticipant-type","encounterByEpisodeofcare","encounterByIncomingreferral",		
																	"encounterByPractitioner");
	
	public static final List<String> organizationAllTestCases = Arrays.asList("organizationByName","organizationByPhonetic","organizationByType","organizationByIdentifier",
																	"organizationByPartof","organizationByActive","organizationByAddress");
	
	public static final List<String> medicationPrescriptionAllTestCases = Arrays.asList("medicationPrescriptionByDatewritten","medicationPrescriptionByEncounter", 
																			"medicationPrescriptionByIdentifier","medicationPrescriptionByMedication", 
																			"medicationPrescriptionByPatient","medicationPrescriptionByStatus","medicationPrescriptionByPrescriber");
	
	public static final List<String> allTestCases = Arrays.asList("allPatientTestCases","allObservationTestCases","allMedicationTestCases","allImmunizationTestCases",
																  "allAllergyIntoleranceTestCases","allConditionTestCases","allEncounterTestCases","allOrganizationTestCases",
																  "allMedicationPrescriptionTestCases");
    
	
	
	public static final Map<String , List<String>> allTestCaseMap = new HashMap<String , List<String>>() {/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	{
	    put("allPatientTestCases", patientAllTestCases);
	    put("allObservationTestCases", observationAllTestCases);
	    put("allMedicationTestCases", medicationAllTestCases);
	    put("allImmunizationTestCases", immunizationAllTestCases);
	    put("allAllergyIntoleranceTestCases", allergyIntoleranceAllTestCases);
	    put("allConditionTestCases", conditionAllTestCases);
	    put("allEncounterTestCases", encounterAllTestCases);
	    put("allOrganizationTestCases", organizationAllTestCases);
	    put("allMedicationPrescriptionTestCases", medicationPrescriptionAllTestCases);
	}};


}
