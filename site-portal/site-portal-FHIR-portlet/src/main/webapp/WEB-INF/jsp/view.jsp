<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil"%>
<%@ page import="javax.portlet.PortletPreferences"%>
<%@ page import="com.liferay.util.PwdGenerator"%>
<%@ page import="com.liferay.portal.service.PortletPreferencesLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>


<portlet:defineObjects />
<portlet:resourceURL var="getFHIR" id="getFHIR" ></portlet:resourceURL>
<portlet:resourceURL var="executeFHIRTestCases" id="executeFHIRTestCases" ></portlet:resourceURL>

<script type="text/javascript">

window.runTestsUrl = "/site-portal-FHIR-portlet/ExecuteFHIRTestCases";
function getFHIRResults()
{
	var jform = $('#FHIRForm');
	//jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
	if(jform.validationEngine('validate'))
	{
		var searchQuery = prepareSearchQuery();
		$('#search-btn').button('loading');
		$('#search-btn').toggleClass('active');
		$.ajax({
		 		dataType: "json",
		 		type:'GET',
		 		url:"<%= getFHIR %>",
		 		data:{
			 		'searchQuery':searchQuery
		 		},
		 		success:function(data)
		 		{
			 		if(data.error == false)
			 		{
		 				var jsonStr = data.result;
			 			var jsonObj = JSON.parse(jsonStr);
			 			var jsonPretty = JSON.stringify(jsonObj, null, '\t');
			 			$("#result").text(jsonPretty);
			 			$("#resultDiv").show();
			 		}else if (data.error == true){
			 			$("#result").text(data.result);
			 			$("#resultDiv").show();
			 		}
			 		$('#search-btn').button('reset');
			 		$('#search-btn').toggleClass('active');
			 		
		 		},
		 		error : function(XMLHttpRequest, textStatus, errorThrown) 
		 		{
			 		$('#result').html("Something went wrong");
			 		$('#search-btn').button('reset');
			 		$('#search-btn').toggleClass('active');
	      		}
		 });
	}else 
	{
		$('#FHIRForm .formError').show(0);
	}
}

</script>


<portlet:defineObjects />

<div id="FHIRClient" class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">FHIR Client</h3>
	</div>

	<div class="panel-body">
        <div class="row">
        	 <form name="testForm" id="FHIRForm">
        	 	 <div class="form-group col-sm-6"> 
          		  	<label for="resource" class="control-label">Select Resource:</label>
            		<select class="validate[required] form-control" name="resource" id="resource" onchange="onResourceChange()">
  						<option value="Patient">Patient Resource</option>
  						<option value="Observation">Observation Resource</option>
  						<option value="Medication">Medication Resource</option>
  						<option value="Immunization">Immunization Resource</option>
  						<option value="AllergyIntolerance">allergyIntolerance Resource</option>
  						<option value="Condition">Condition Resource</option>
  						<option value="Encounter">Encounter Resource</option>
  						<option value="Organization">Organization Resource</option>
  						<option value="MedicationPrescription">MedicationPrescription Resource</option>
		   			</select>
		   		</div>
		   		<div class="form-group col-sm-6" style="padding-left: 0px;"> 
          		  <label for="serverBaseURL">Enter FHIR URL:</label> 
              	  <input id="serverBaseURL" name="serverBaseURL" type="text" value="" 
              	         class="validate[required,custom[url]] form-control" tabindex="1"> 
               </div>            
             </form>		 
        </div>
		
		<div class="row" hidden="true" id="textRow">
		   	<div class="col-sm-6">
			  	<h4>Search Parameters <small>Optionally add parameter(s) to the search</small></h4>
		   	</div>
		</div>
		<div id="search-param-rows">
		</div>
		</br>
		<div class="row">
			<div class="col-sm-2">
				<button type="button" id="search-btn"  onclick="getFHIRResults();" 
						class="btn btn-primary btn-block" data-loading-text="Searching <i class=icon-spin icon-refresh'/>">
				<span class="glyphicon glyphicon-search"></span>Search
				</button>
			</div>												
		</div>
			
		<div class="row" hidden="true" id="resultDiv">
  			<div class="panel-body">
  			 	 <label for="Search Param Input">Results</label>
   				 <pre id="result"></pre>
  			</div>
	   	</div>
	</div>
</div>

<div id="FHIRTestCaseWidget" class="panel panel-default"> 
     <div class="panel-heading">
          <h3 class="panel-title">FHIR Server Test Cases</h3>
     </div>
     <div class="panel-body">
     <h4>Directions</h4>
     <ol> 
          <li>Enter Server Base URL</li> 
          <li>Select the FHIR resource you want to test</li> 
          <li>Select the test case and execute</li>
      </ol> 
      <div class="well"> 
        <form name="FHIRTestCaseForm" id="FHIRTestCaseForm"> 
          <label for="fhirResource">Select the resource</label> 
          <select id="testCaseResource" name="testCaseResource" class="form-control" tabindex="1" onchange="onFhirTestCaseResourceChange()"> 
            <option value="Patient">Patient Resource</option>
  			<option value="Observation">Observation Resource</option>
  			<option value="Medication">Medication Resource</option>
  			<option value="Immunization">Immunization Resource</option>
  			<option value="AllergyIntolerance">allergyIntolerance Resource</option>
  			<option value="Condition">Condition Resource</option> 
  			<option value="Encounter">Encounter Resource</option>
  			<option value="Organization">Organization Resource</option>
  			<option value="MedicationPrescription">MedicationPrescription Resource</option>
         </select> 
         <p></p>
          <label for="fhirServerURL">Enter Your Endpoint URL:</label> 
          <input id="fhirServerURL" name="fhirServerURL" type="text" value="" class="validate[required,custom[url]] form-control" tabindex="1"> 
          <p></p>
          <label for="testCase">Select a Test Case:</label> 
          <select id="testCaseList" name="testCaseName" class="validate[required] form-control" tabindex="1"> 
          </select>  
          <p> </p><hr> 
        </form> 
        <button id="querySubmit" class="btn btn-primary start" onclick="return false;"  tabindex="1" 
        		data-loading-text="<i id='refreshIcon' class='glyphicon glyphicon-refresh'/> Running Test Cases..."> 
          <span class="glyphicon glyphicon-ok"></span> <span>Run Test Case</span> 
        </button> <p></p> 
      </div> 
    </div> 
 </div>
 
 
 <div class="modal modal-wide fade" id="resultsDialog" tabindex="-1" role="dialog" aria-labelledby="resultModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4>FHIR Server Test Results</h4>
				</div>
		    	<div class="modal-body" id="PDResult">
	  			</div>
				<div class="modal-footer">
			     	<button type="button" class="btn btn-default" data-dismiss="modal">Close Results</button>
				</div>
			</div>
		</div>
	</div>




