var numRows = 0;

var patientResourceOptionList = {"searchParam":[{"name":"_id","type":"string","description":"The resource identity"},
                                                {"name":"_language","type":"token","description":"The resource language"},
                                                {"name":"identifier","type":"token","description":"A patient identifier"},
                                                {"name":"active","type":"token","description":"Whether the patient record is active"},
                                                {"name":"name","type":"string","description":"A portion of either family or given name of the patient"},
                                                {"name":"family","type":"string","description":"A portion of the family name of the patient"},
                                                {"name":"given","type":"string","description":"A portion of the given name of the patient"},
                                                {"name":"phonetic","type":"string","description":"A portion of either family or given name using some kind of phonetic matching algorithm"},
                                                {"name":"telecom","type":"token","description":"The value in any kind of telecom details of the patient"},
                                                {"name":"address","type":"string","description":"An address in any kind of address/part of the patient"},
                                                {"name":"gender","type":"token","description":"Gender of the patient"},
                                                {"name":"birthdate","type":"date","description":"The patient's date of birth"},
                                                {"name":"organization","type":"reference","description":"The organization at which this person is a patient"},
                                                {"name":"careprovider","type":"reference","description":"Patient's nominated care provider, could be a care manager, not the organization that manages the record"},
                                                {"name":"animal-species","type":"token","description":"The species for animal patients"},
                                                {"name":"animal-breed","type":"token","description":"The breed for animal patients"},
                                                {"name":"link","type":"reference","description":"All patients linked to the given patient"},
                                                {"name":"deceased","type":"token","description":"This patient has been marked as deceased, or as a death date entered"},
                                                {"name":"deathdate","type":"date","description":"The date of death has been provided and satisfies this search value"}]};

var observationResourceOptionList ={"searchParam":[{"name":"code","type":"token","description":"The code of the observation type"},
                                                   {"name":"value-quantity","type":"quantity","description":"The value of the observation, if the value is a Quantity, or a SampledData"},
                                                   {"name":"value-concept","type":"token","description":"The value of the observation"},
                                                   {"name":"value-date","type":"date","description":"The value of the observation, if the value is a Period"},
                                                   {"name":"value-string","type":"string","description":"The value of the observation, if the value is a string"},
                                                   {"name":"date","type":"date","description":"Obtained date/time. If the obtained element is a period"},
                                                   {"name":"status","type":"token","description":"The status of the observation"},
                                                   {"name":"reliability","type":"token","description":"The reliability of the observation"},
                                                   {"name":"subject","type":"reference","description":"The subject that the observation is about"},
                                                   {"name":"performer","type":"reference","description":"Who performed the observation"},
                                                   {"name":"specimen","type":"reference","description":"specimen"},
                                                   {"name":"related-type","type":"token","description":"related-type"},
                                                   {"name":"related-target","type":"reference","description":"related-target"},
                                                   {"name":"encounter","type":"reference","description":"Healthcare event related to the observation"},
                                                   {"name":"data-absent-reason","type":"token","description":"The reason why the expected value in the element Observation.value[x] is missing"},
                                                   {"name":"patient","type":"reference","description":"The subject that the observation is about (if patient)"},
                                                   {"name":"identifier","type":"token","description":"The unique Id for a particular observation"},
                                                   {"name":"device","type":"reference","description":"The Device that generated the observation data."},
                                                   {"name":"code-value-quantity","type":"composite","description":"Both code and one of the value parameters"},
                                                   {"name":"code-value-concept","type":"composite","description":"Both code and one of the value parameters"},
                                                   {"name":"code-value-date","type":"composite","description":"Both code and one of the value parameters"},
                                                   {"name":"code-value-string","type":"composite","description":"Both code and one of the value parameters"},
                                                   {"name":"related-target-related-type","type":"composite","description":"Related Observations - search on related-type and related-target together"}]};

var medicationResourceOptionList ={"searchParam":[{"name":"code","type":"token","description":"The code of the observation type"},
                                                   {"name":"name","type":"string","description":"Name of the Medication"},
                                                   {"name":"manufacturer","type":"reference","description":"Manufacturer of the Medication"},
                                                   {"name":"form","type":"token","description":"Form For Medication"},
                                                   {"name":"ingredient","reference":"string","description":"Ingredient of the Medication"},
                                                   {"name":"container","type":"token","description":"Container of the Medication"},
                                                   {"name":"content","type":"reference","description":"Content of the Medication"}]};

var immunizationResourceOptionList ={"searchParam":[{"name":"date","type":"date","description":"Vaccination  (non)-Administration Date"},
                                                    {"name":"dose-sequence","type":"number","description":"Immunization.vaccinationProtocol.doseSequence"},
                                                    {"name":"identifier","type":"token","description":"Identifier Value"},
                                                    {"name":"location","type":"reference","description":"The service delivery location or facility in which the vaccine to be administered"},
                                                    {"name":"lot-number","reference":"string","description":"Vaccine Lot Number"},
                                                    {"name":"manufacturer","type":"reference","description":"Vaccine Manufacturer"},
                                                    {"name":"performer","type":"reference","description":"The practitioner who administered the vaccination"},
                                                    {"name":"reaction","type":"reference","description":"Reaction value"},
                                                    {"name":"reaction-date","type":"date","description":"Reaction Date Value"},
                                                    {"name":"reason","type":"token","description":"Reason For Immunization"},
                                                    {"name":"notgiven","type":"token","description":"Administrations which were not given"},
                                                    {"name":"requester","type":"reference","description":"The practitioner who ordered the vaccination"},
                                                    {"name":"patient","type":"token","description":"The patient for the vaccination record"},
                                                    {"name":"vaccine-type","type":"token","description":"Vaccine Product Type Administered"},
                                                    {"name":"subject","type":"reference","description":"The patient for the vaccination record"}]};

var allergyIntoleranceResourceOptionList ={"searchParam":[{"name":"substance","type":"token","description":"Substance Value"},
                                                    {"name":"status","type":"token","description":"enter status value"},
                                                    {"name":"criticality","type":"token","description":"criticality value"},
                                                    {"name":"type","type":"reference","description":"type value"},
                                                    {"name":"category","reference":"string","description":"category value"},
                                                    {"name":"last-date","type":"date","description":"last-date value"},
                                                    {"name":"manifestation","type":"reference","description":"manifestation Value"},
                                                    {"name":"onset","type":"reference","description":"onset value"},
                                                    {"name":"duration","type":"date","description":"Duration value"},
                                                    {"name":"severity","type":"token","description":"severity value"},
                                                    {"name":"route","type":"token","description":"exposureRoute value"},
                                                    {"name":"identifier","type":"reference","description":"identifier value"},
                                                    {"name":"date","type":"token","description":"recordedDate"},
                                                    {"name":"recorder","type":"token","description":"recoreder value"},
                                                    {"name":"patient","type":"reference","description":"Patience reference value"},
                                                    {"name":"reporter","type":"reference","description":"reporter value"}]};

var conditionResourceOptionList ={"searchParam":[{"name":"code","type":"token","description":"Code for the condition"},
                                                 {"name":"clinicalstatus","type":"token","description":"The clinical status of the condition"},
                                                 {"name":"severity","type":"token","description":"The severity of the condition"},
                                                 {"name":"category","type":"reference","description":"The category of the condition"},
                                                 {"name":"onset","reference":"string","description":"Date related onsets (dateTime and Period)"},
                                                 {"name":"onset-info","type":"date","description":"Other onsets (boolean, age, range, string)"},
                                                 {"name":"encounter","type":"reference","description":"encounter Value"},
                                                 {"name":"asserter","type":"reference","description":"asserter value"},
                                                 {"name":"date-asserted","type":"date","description":"date-asserted value"},
                                                 {"name":"evidence","type":"token","description":"evidence value"},
                                                 {"name":"stage","type":"token","description":"stage value"},
                                                 {"name":"patient","type":"reference","description":"patient associated"},
                                                 {"name":"subject","type":"token","description":"subject associated"},
                                                 {"name":"dueto-code","type":"token","description":"dueto-code value"},
                                                 {"name":"dueto-item","type":"reference","description":"dueto-item value"},
                                                 {"name":"following-code","type":"reference","description":"following-code value"},
                                                 {"name":"following-item","type":"reference","description":"following-item value"}]};

var encounterResourceOptionList ={"searchParam":[{"name":"identifier","type":"token","description":"The identifier for the encounter"},
                                                 {"name":"status","type":"token","description":"The clinical status of the encounter"},
                                                 {"name":"date","type":"date","description":"A date within the period the Encounter lasted"},
                                                 {"name":"patient","type":"reference","description":"The patient for the Encounter"},
                                                 {"name":"fulfills","reference":"string","description":"Fulfills for the Encounter"},
                                                 {"name":"length","type":"number","description":"Length of encounter in days"},
                                                 {"name":"reason","type":"token","description":"Reson for the Encounter"},
                                                 {"name":"indication","type":"reference","description":"Indication for the Encounter"},
                                                 {"name":"location","type":"reference","description":"Location for the Encounter"},
                                                 {"name":"location-period","type":"date","description":"location-period of the Encounter"},
                                                 {"name":"type","type":"token","description":"Type of the Encounter"},
                                                 {"name":"special-arrangement","token":"reference","description":"special-arrangement Value"},
                                                 {"name":"part-of","type":"reference","description":"Part-of Value"},
                                                 {"name":"participant","type":"reference","description":"participant Value"},
                                                 {"name":"participant-type","type":"token","description":"participant-type Value"},
                                                 {"name":"episodeofcare","type":"reference","description":"episodeofcare Value"},
                                                 {"name":"incomingreferral","type":"reference","description":"incomingreferral Value"},
                                                 {"name":"practitioner","type":"reference","description":"practitioner Value"}]};

var organizationResourceOptionList ={"searchParam":[{"name":"name","type":"String","description":"A portion of the organization's name"},
                                                  {"name":"phonetic","type":"string","description":"A portion of the organization's name using some kind of phonetic matching algorithm"},
                                                  {"name":"type","type":"token","description":"A code for the type of organization"},
                                                  {"name":"identifier","type":"token","description":"Any identifier for the organization (not the accreditation issuer's identifier)"},
                                                  {"name":"partof","reference":"reference","description":"Search all organizations that are part of the given organization"},
                                                  {"name":"active","type":"token","description":"Whether the organization's record is active"},
                                                  {"name":"address","type":"string","description":"A (part of the) address of the Organization"}]};

var medicationPrescriptionResourceOptionList ={"searchParam":[{"name":"datewritten","type":"date","description":"Return prescriptions written on this date"},
                                                   {"name":"encounter","type":"reference","description":"Return prescriptions with this encounter identity"},
                                                   {"name":"identifier","type":"token","description":"Return prescriptions with this external identity"},
                                                   {"name":"medication","type":"reference","description":"Code for medicine or text in medicine name"},
                                                   {"name":"patient","reference":"reference","description":"The identity of a patient to list dispenses  for"},
                                                   {"name":"status","type":"token","description":"Status of the prescription"},
                                                   {"name":"prescriber","type":"reference","description":"Prescriber of the Medication"}]};

var patientTestCaseOptionList = {"searchParam":[{"value":"allPatientTestCases","description":"Run all Test Cases for Patient Resource"},
                                                {"value":"patientById","description":"Search Patient by Id"},
                                                {"value":"patientByLanguage","description":"Search Patient by Language"},
                                                {"value":"patientByIdentifier","description":"Search Patient by Identifier"},
                                                {"value":"patientByActive","description":"Search Patient by status"},
                                                {"value":"patientByName","description":"Search Patient by name, Either first name or family name"},
                                                {"value":"patientByFamily","description":"Search Patient by family name "},
                                                {"value":"patientByGiven","description":"Search Patient by given name"},
                                                {"value":"patientByPhonetic","description":"Search Patient by phonetic"},
                                                {"value":"patientByTelecom","description":"Search Patient by telephone number"},
                                                {"value":"patientByAddress","description":"Search Patient by address"},
                                                {"value":"patientByGender","description":"Search Patient by gender"},
                                                {"value":"patientByBirthdate","description":"Search Patient by date of birth"},
                                                {"value":"patientByOrganization","description":"Search Patient by organization"},
                                                {"value":"patientByCareprovider","description":"Search Patient by careprovider"},
                                                {"value":"patientByAnimal-species","description":"Search Patient by animal species"},
                                                {"value":"patientByAnimal-breed","description":"Search Patient by animla breed"},
                                                {"value":"patientByLink","description":"Search Patient by link"},
                                                {"value":"patientByDeceased","description":"Search Patient by deceased status"},
                                                {"value":"patientByDeathdate","description":"Search Patient by deathdate"}]};

var observationTestCaseOptionList ={"searchParam":[{"value":"allObservationTestCases","description":"Run all Test Cases for Observation Resource"},
                                                   {"value":"observationByCode","description":"Search Observation by Code"},
                                                   {"value":"observationByValue-quantity","description":"Search Observation by Value-Quantity Value-quantity"},
                                                   {"value":"observationByValue-concept","description":"Search Observation by Value-concept"},
                                                   {"value":"observationByValue-date","description":"Search Observation by Value-date"},
                                                   {"value":"observationByValue-string","description":"Search Observation by Value-string"},
                                                   {"value":"observationByDate","description":"Search Observation by Date"},
                                                   {"value":"observationByStatus","description":"Search Observation by Status"},
                                                   {"value":"observationByReliability","description":"Search Observation by Reliability"},
                                                   {"value":"observationBySubject","description":"Search Observation by Subject"},
                                                   {"value":"observationByPerformer","description":"Search Observation by Performer"},
                                                   {"value":"observationBySpecimen","description":"Search Observation by Specimen"},
                                                   {"value":"observationByRelated-type","description":"Search Observation by Related-type"},
                                                   {"value":"observationByRelated-target","description":"Search Observation by Related-target"},
                                                   {"value":"observationByEncounter","description":"Search Observation by Encounter"},
                                                   {"value":"observationByData-absent-reason","description":"Search Observation by Data-absent-reason"},
                                                   {"value":"observationByPatient","description":"Search Observation by Patient"},
                                                   {"value":"observationByIdentifier","description":"Search Observation by Identifier"},
                                                   {"value":"observationByDevice","description":"Search Observation by Device"},
                                                   {"value":"observationByCode-value-quantity","description":"Search Observation by Code-value-quantity"},
                                                   {"value":"observationByCode-value-concept","description":"Search Observation by Code-value-concept"},
                                                   {"value":"observationByCode-value-date","description":"Search Observation by Code-value-date"},
                                                   {"value":"observationByCode-value-string","description":"Search Observation by Code-value-string"},
                                                   {"value":"observationByRelated-target-related-type","description":"Search Observation by Related-target-related-type"}]};

var medicationTestCaseOptionList ={"searchParam":[{"value":"allMedicationTestCases","description":"Run all Test Cases for Medication Resource"},
                                                  {"value":"medicationByCode","description":"Search Medication by code"},
                                                  {"value":"medicationByValue","description":"Search Medication by value"},
                                                  {"value":"medicationByManufacturer","description":"Search Medication by manufacturer"},
                                                  {"value":"medicationByForm","description":"Search Medication by form"},
                                                  {"value":"medicationByIngredient","description":"Search Medication by ingredient"},
                                                  {"value":"medicationByContainer","description":"Search Medication by container"},
                                                  {"value":"medicationByContent","description":"Search Medication by content"}]};

var immunizationTestCaseOptionList ={"searchParam":[{"value":"allImmunizationTestCases","description":"Run all Test Cases for Immunization Resource"},
                                                    {"value":"immunizationByDate","description":"Search Immunization by Date"},
                                                    {"value":"immunizationByDose-sequence","description":"Search Immunization by Dose-sequence"},
                                                    {"value":"immunizationByIdentifier","description":"Search Immunization by Identifier"},
                                                    {"value":"immunizationByLocation","description":"Search Immunization by Location"},
                                                    {"value":"immunizationByLot-number","description":"Search Immunization by Lot-number"},
                                                    {"value":"immunizationByManufacturer","description":"Search Immunization by Manufacturer"},
                                                    {"value":"immunizationByPerformer","description":"Search Immunization by Performer"},
                                                    {"value":"immunizationByReaction","description":"Search Immunization by Reaction"},
                                                    {"value":"immunizationByReaction-date","description":"Search Immunization by Reaction-date"},
                                                    {"value":"immunizationByReason","description":"Search Immunization by Reason"},
                                                    {"value":"immunizationByNotgiven","description":"Search Immunization by Notgiven"},
                                                    {"value":"immunizationByRequester","description":"Search Immunization by Requester"},
                                                    {"value":"immunizationByPatient","description":"Search Immunization by Patient"},
                                                    {"value":"immunizationByVaccine-type","description":"Search Immunization by Vaccine-type"},
                                                    {"value":"immunizationBySubject","description":"Search by Immunization by Subject"}]};


var allergyIntoleranceTestCaseOptionList ={"searchParam":[{"value":"allAllergyIntoleranceTestCases","description":"Run all Test Cases for Allergy Intollerance Resource"},
                                                          {"value":"allergyIntoleranceBySubstance","description":"Search Allergy Intolerance by Substance"},
                                                          {"value":"allergyIntoleranceByStatus","description":"Search Allergy Intolerance by Status"},
                                                          {"value":"allergyIntoleranceByCriticality","description":"Search Allergy Intolerance by Criticality"},
                                                          {"value":"allergyIntoleranceByType","description":"Search Allergy Intolerance by Type"},
                                                          {"value":"allergyIntoleranceByCategory","description":"Search Allergy Intolerance by Category"},
                                                          {"value":"allergyIntoleranceByLast-date","description":"Search Allergy Intolerance by Last-date"},
                                                          {"value":"allergyIntoleranceByManifestation","description":"Search Allergy Intolerance by Manifestation"},
                                                          {"value":"allergyIntoleranceByOnset","description":"Search Allergy Intolerance by Onset"},
                                                          {"value":"allergyIntoleranceByDuration","description":"Search Allergy Intolerance by Duration"},
                                                          {"value":"allergyIntoleranceBySeverity","description":"Search Allergy Intolerance by Severity"},
                                                          {"value":"allergyIntoleranceByRoute","description":"Search Allergy Intolerance by Route"},
                                                          {"value":"allergyIntoleranceByIdentifier","description":"Search Allergy Intolerance by Identifier"},
                                                          {"value":"allergyIntoleranceByDate","description":"Search Allergy Intolerance by Date"},
                                                          {"value":"allergyIntoleranceByRecorder","description":"Search Allergy Intolerance by Recorder"},
                                                          {"value":"allergyIntoleranceByPatient","description":"Search Allergy Intolerance by Patient"},
                                                          {"value":"allergyIntoleranceByReporter","description":"Search Allergy Intolerance by Reporter"}]};

var conditionTestCaseOptionList ={"searchParam":[{"value":"allConditionTestCases","description":"Run all Test Cases for Condition Resource"},
                                                 {"value":"conditionByCode","description":"Search Condition by Code"},
                                                 {"value":"conditionByClinicalstatus","description":"Search Condition by Clinicalstatus"},
                                                 {"value":"conditionBySeverity","description":"Search Condition by Severity"},
                                                 {"value":"conditionByCategory","description":"Search Condition by Category"},
                                                 {"value":"conditionByOnset","description":"Search Condition by Onset"},
                                                 {"value":"conditionByOnset-info","description":"Search Condition by Onset-info"},
                                                 {"value":"conditionByEncounter","description":"Search Condition by Encounter"},
                                                 {"value":"conditionByAsserter","description":"Search Condition by Asserter"},
                                                 {"value":"conditionByDate-asserted","description":"Search Condition by Date-asserted"},
                                                 {"value":"conditionByEvidence","description":"Search Condition by Evidence"},
                                                 {"value":"conditionByStage","description":"Search Condition by Stage"},
                                                 {"value":"conditionByPatient","description":"Search Condition by Patient"},
                                                 {"value":"conditionBySubject","description":"Search Condition by Subject"},
                                                 {"value":"conditionByDueto-code","description":"Search Condition by Dueto-code"},
                                                 {"value":"conditionByDueto-item","description":"Search Condition by Dueto-item"},
                                                 {"value":"conditionByFollowing-code","description":"Search Condition by Following-code"},
                                                 {"value":"conditionByFollowing-item","description":"Search Condition by Following-item"}]};


var encounterTestCaseOptionList ={"searchParam":[{"value":"allEncounterTestCases","description":"Run all Test Cases for Encounter Resource"},
                                                 {"value":"encounterByIdentifier","description":"Search Encounter by Identifier"},
                                                 {"value":"encounterByStatus","description":"Search Encounter by Status"},
                                                 {"value":"encounterByDate","description":"Search Encounter by Date"},
                                                 {"value":"encounterByPatient","description":"Search Encounter by Patient"},
                                                 {"value":"encounterByFulfills","description":"Search Encounter by Fulfills"},
                                                 {"value":"encounterByLength","description":"Search Encounter by Length"},
                                                 {"value":"encounterByReason","description":"Search Encounter by Reason"},
                                                 {"value":"encounterByIndication","description":"Search Encounter by Indication"},
                                                 {"value":"encounterByLocation","description":"Search Encounter by Location"},
                                                 {"value":"encounterByLocation-period","description":"Search Encounter by Location-period"},
                                                 {"value":"encounterByType","description":"Search Encounter by Type"},
                                                 {"value":"encounterBySpecial-arrangement","description":"Search Encounter by Special-arrangement"},
                                                 {"value":"encounterByPart-of","description":"Search Encounter by Part-of"},
                                                 {"value":"encounterByParticipant","description":"Search Encounter by Participant"},
                                                 {"value":"encounterByParticipant-type","description":"Search Encounter by Participant-type"},
                                                 {"value":"encounterByEpisodeofcare","description":"Search Encounter by Episodeofcare"},
                                                 {"value":"encounterByIncomingreferral","description":"Search Encounter by Incomingreferral"},
                                                 {"value":"encounterByPractitioner","description":"Search Encounter by Practitioner"}]};

var organizationTestCaseOptionList ={"searchParam":[{"value":"allOrganizationTestCases","description":"Run all Test Cases for Organization Resource"},
                                                    {"value":"organizationByName","description":"Search Organization by Name"},
                                                    {"value":"organizationByPhonetic","description":"Search Organization by Phonetic"},
                                                    {"value":"organizationByType","description":"Search Organization by Type"},
                                                    {"value":"organizationByIdentifier","description":"Search Organization by Identifier"},
                                                    {"value":"organizationByPartof","description":"Search Organization by Partof"},
                                                    {"value":"organizationByActive","description":"Search Organization by Active"},
                                                    {"value":"organizationByAddress","description":"Search Organization by Address"}]};

var medicationPrescriptionTestCaseOptionList ={"searchParam":[{"value":"allMedicationPrescriptionTestCases","description":"Run all Test Cases for Medication Prescription Resource"},
                                                              {"value":"medicationPrescriptionByDatewritten","description":"Search MedicationPrescription By Datewritten"},
                                                              {"value":"medicationPrescriptionByEncounter","description":"Search MedicationPrescription By Encounter"},
                                                              {"value":"medicationPrescriptionByIdentifier","description":"Search MedicationPrescription By Identifier"},
                                                              {"value":"medicationPrescriptionByMedication","description":"Search MedicationPrescription By Medication"},
                                                              {"value":"medicationPrescriptionByPatient","description":"Search MedicationPrescription By Patient"},
                                                              {"value":"medicationPrescriptionByStatus","description":"Search MedicationPrescription By Status"},
                                                              {"value":"medicationPrescriptionByPrescriber","description":"Search MedicationPrescription By Prescriber"}]};

var errorHtmlString = "<html><head><title> Export Error </title></head><body>" +
					  "<font color='RED' size='3' style='font-weight: bold;'>Unexpected Error: Something went wrong with FHIR server</font></body></html>";


$(function(){
	
	onResourceChange(); 
	$("#testCaseList").append(populateFhirTestCaseOptions($("#testCaseResource").val()));
	$("#querySubmit").click(function (e) {
		
		e.preventDefault();
		var jform = $('#FHIRTestCaseForm');
		var $icon = $('#refreshIcon');
	    animateClass = "glyphicon-refresh-animate";
		jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
		if(jform.validationEngine('validate'))
		{
		   $('#FHIRTestCaseForm .formError').hide(0);
		   $('#querySubmit').button('loading');
		   $icon.addClass( animateClass );
		   $.ajax({
			   		type: "POST",
			   		url: window.runTestsUrl,
			   		data:{
				 		'fhirServerURL':$("#fhirServerURL").val(),
				 		'testCaseName':$('#testCaseList option:selected').text(),
				 		'testCaseValue':$("#testCaseList").val()
			 		},
			   		success: function(data) {
			   				$("#PDResult").html(data);
			   				$("#resultsDialog").modal("show");
			   				$('#querySubmit').button('reset');
			   				$icon.removeClass( animateClass );
			   		},
			   		error: function(jqXHR, textStatus, errorThrown) {
			   			$("#PDResult").html(errorHtmlString);	
			   			$("#resultsDialog").modal("show");
			   			$('#querySubmit').button('reset');
			   			$icon.removeClass( animateClass );
			   		}
	        });	
		}
		else 
		{
			$('#FHIRTestCaseForm .formError').show(0);
		}
	});
 });
	
function addSearchParamRow() {
	
	var nextRow = numRows++;
	var selectedResource = $("#resource").val();
	var resourceOptions = populateResourceOptions(selectedResource);
	var select = $('<select/>', { id: 'search-param-name-' + nextRow,'class': 'form-control' });
	select.append(resourceOptions);	
	var plusBtn = $('<button />', {type:'button', 'class':'btn btn-success btn-block'});

	
	var dropDown = $('<ul />' , {'class' : 'dropdown-menu' , role : 'menu' }).
	               append($('<li />').append($('<a>Matches</a>')),
	                      $('<li />').append($('<a>Exactly</a>')));
	
	var inputGrpButton = $('<div />', { 'class': 'col-sm-5 input-group' }).append(
			$('<div />', {'class':'input-group-btn'}).append(
				$('<button />', {id:'search-param-valueType-'+ nextRow, 'class':'btn btn-default dropdown-toggle', 'data-toggle':'dropdown'}).append(
					'Matches',
					$('<span class="caret" style="margin-left: 5px;"></span>')
				),
				dropDown
			),
	    	$('<input />', { id:'search-param-value-'+ nextRow, placeholder: 'placeholderText', type: 'text', 'class': 'form-control' })
    	);
	
	
	plusBtn.append($('<span />', {'class':'glyphicon glyphicon-plus'}));
	plusBtn.isAdd = true;

	var rowDiv = $('<div />', { 'class': 'row', style:'margin-top: 2px;',  id: 'search-param-row-' + nextRow }).append(
			$('<div />', { 'class': 'col-sm-1' }).append(plusBtn),
			$('<div />', { 'class': 'col-sm-5' }).append(select),inputGrpButton);
	
	$("#search-param-rows").append(rowDiv);
	
	$(".dropdown-menu li a").click(function(){
		 var selText = $(this).text();
		    $(this).parents('.input-group-btn').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
		  $(this).parents(".input-group-btn").find('.btn').val($(this).text());
		});
	
	plusBtn.click(function() {
		plusBtn.hide();
		addSearchParamRow();
	});
}

function populateResourceOptions(selectedResource)
{
    var optionList = "";
    var resourceOptionList = null;
    if(selectedResource == "Patient")
    {
       resourceOptionList = patientResourceOptionList;
    }else if (selectedResource == "Observation")
    {
       resourceOptionList = observationResourceOptionList;
    }else if (selectedResource == "Medication")
    {
    	resourceOptionList = medicationResourceOptionList;
    }else if (selectedResource == "Immunization")
    {
    	resourceOptionList = immunizationResourceOptionList;
    }else if (selectedResource == "AllergyIntolerance")
    {
    	resourceOptionList = allergyIntoleranceResourceOptionList;
    }else if (selectedResource == "Condition")
    {
    	resourceOptionList = conditionResourceOptionList;
    }else if (selectedResource == "Encounter")
    {
    	resourceOptionList = encounterResourceOptionList;
    }else if (selectedResource == "Organization")
    {
    	resourceOptionList = organizationResourceOptionList;
    }else if (selectedResource == "MedicationPrescription")
    {
    	resourceOptionList = medicationPrescriptionResourceOptionList;
    }
    
    optionList += "<option title='Select' value='-1'>Please Select</option>";
    $(resourceOptionList.searchParam).each(function(){
		optionList += "<option value='"+ this.name +"'>" + this.name + "-" + this.description+ "</option>";
	});
	return optionList;
}

function populateFhirTestCaseOptions(selectedFhirResource)
{
    var optionList = "";
    var fhirTestCaseOptionList = null;
    if(selectedFhirResource == "Patient")
    {
    	fhirTestCaseOptionList = patientTestCaseOptionList;
    }else if (selectedFhirResource == "Observation")
    {
    	fhirTestCaseOptionList = observationTestCaseOptionList;
    }else if (selectedFhirResource == "Medication")
    {
    	fhirTestCaseOptionList = medicationTestCaseOptionList;
    }else if (selectedFhirResource == "Immunization")
    {
    	fhirTestCaseOptionList = immunizationTestCaseOptionList;
    }else if (selectedFhirResource == "AllergyIntolerance")
    {
    	fhirTestCaseOptionList = allergyIntoleranceTestCaseOptionList;
    }else if (selectedFhirResource == "Condition")
    {
    	fhirTestCaseOptionList = conditionTestCaseOptionList;
    }else if (selectedFhirResource == "Encounter")
    {
    	fhirTestCaseOptionList = encounterTestCaseOptionList;
    }else if (selectedFhirResource == "Organization")
    {
    	fhirTestCaseOptionList = organizationTestCaseOptionList;
    }else if (selectedFhirResource == "MedicationPrescription")
    {
    	fhirTestCaseOptionList = medicationPrescriptionTestCaseOptionList;
    }
    
    $(fhirTestCaseOptionList.searchParam).each(function(){
		optionList += "<option value='"+ this.value +"'>" +this.description+ "</option>";
	});
	return optionList;
}

function onResourceChange()
{
    $('#search-param-rows').children().remove();
    $('#resultDiv').hide();
    
    if($("#resource").val() != -1)
    {
    	$('#textRow').show();
    }else 
    {
    	$('#textRow').hide();
    }
    
    numRows = 0;
    
    if($("#resource").val() != -1)
    {
      addSearchParamRow();
    }
}

function onFhirTestCaseResourceChange()
{
	$('#testCaseList').find('option').remove();
	$("#testCaseList").append(populateFhirTestCaseOptions($("#testCaseResource").val()));
}

function prepareSearchQuery()
{
   if($("#resource").val() == -1)
   {
      alert("Please select a Resource to search");
      return
   }
   
   if($("#serverBaseURL").val() == '')
   {
      alert("Please enter server URI");
      return;
   }
   
   var searchQuery = $("#serverBaseURL").val();
   searchQuery += "/" + $("#resource").val();
   var addQuestionMark = true; 
   for (var i = 0; i < numRows; i++) {
      
      if($("#search-param-name-"+ i).val() != -1 && $("#search-param-value-"+ i).val() != ''){
      
         var appendValue = addQuestionMark ? "?" : "&" ;
         searchQuery += appendValue + $("#search-param-name-"+ i).val() + "=" + $("#search-param-value-"+ i).val();
         addQuestionMark = false;
      };
   
   }
   return searchQuery;
};





	
	
