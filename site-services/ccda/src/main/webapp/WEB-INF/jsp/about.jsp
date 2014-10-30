<% String rootContext = request.getRequestURL().toString().replace("/About", ""); %>

<html>
<head>
	<title>CCDA Validation Services API</title>
	
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	
	<style>
	
		.row {
			margin-left: 30px;
			margin-right:30px;
		}
			
		.page
		{
			margin-left: 20px;
		}
		
	
	</style>
	
</head>

<body>
<div class="jumbotron">
  <h1>CCDA Validation Services API</h1>
  <p>The goal of the SITE C-CDA Validator is to validate conformance of C-CDA documents to the standard in order to promote interoperability. It is a resource to validate the conformance of C-CDA documents to Meaningful Use Stage 2 (MU2) criteria, or the base HL7 C-CDA Implementation Guide.</p>
</div>

<div class="row">
	<div class="col-lg-9 col-md-9 col-sm-9">
		<div id="validateCode" class="page-header">
  			<h1>Validate C-CDA</h1>
		</div>
		
		<div id="validateCodePage" class="page">
  			
  			
  			<h2>URL</h2>
  			<p><%= rootContext %>/&lt;Version code&gt;  			  			
  			<h2>Validator Versions</h2>
  			<p>The following versions are supported:</p>
			<table>
				<tr>
					<td><b>C-CDA Validator</b></td><td><b>Version code</b></td>
				</tr>
				<tr>
					<td>C-CDA Version 1.1</td><td>r1.1</td>
				</tr>
				<tr>
					<td>C-CDA Version 2.0</td><td>r2.0</td>
				</tr>
				<!--<tr>
					<td>Reconciled C-CDA</td><td>Reconciled</td>
				</tr>
				<tr>
					<td>Reference C-CDA</td><td>Reference</td>
				</tr>
				
				<tr>
					<td>Super C-CDA</td><td>Super</td>
				</tr>-->
				
			</table>
  			<br>
  			<br>
  			<br>
  			<br>
  			<h2>C-CDA Release 1.1 Validator</h2>
  			
  			<h3>Parameters</h3>
  			
			<p>file = The name of the multipart/form-data parameter that contains attachments must be &quot;file&quot;</p>

			<p>type_val = a parameter indicating the type of document being analyzed<br><br></p>
  			
  			<h3>Type Values</h3>
  			
  			
  			<table>
			 <tr>
			 	<td>
			 		<p><b><span>Document Type</span></b></p>
			 	</td>
			 	<td>
			 		<p><b><span>type_val</span></b></p>
			  	</td>
			  	</tr>
			  	<tr>
			  		<td>
			  			<p><span>Clinical Office Visit Summary - MU2 170.314(e)(2) - Clinical Summary</span></p>
			  		</td>
			  		<td>
			  			<p><span>ClinicalOfficeVisitSummary</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Ambulatory Summary - MU2 170.314(b)(2) Transition of Care/Referral Summary - For Ambulatory Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareAmbulatorySummaryb2</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Ambulatory Summary - MU2 170.314(b)(7) Data Portability - For Ambulatory Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareAmbulatorySummaryb7</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Ambulatory Summary -
			  			MU2 170.314(b)(1) Transition of Care Receive - For Ambulatory Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareAmbulatorySummaryb1</span></p>
			  		</td>
			 	</tr>
				<tr>
			  		<td>
			  			<p><span>Transitions Of Care Inpatient Summary - MU2
			  			170.314(b)(2) Transition of Care/Referral Summary - For Inpatient Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareInpatientSummaryb2</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Inpatient Summary - MU2
			 			170.314(b)(7) Data Portability - For Inpatient Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareInpatientSummaryb7</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Inpatient Summary - MU2
			  			170.314(b)(1) Transition of Care Receive - For Inpatient Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareInpatientSummaryb1</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>VDT Ambulatory Summary - MU2 170.314 (e)(1)
			  			Ambulatory Summary</span></p>
			  		</td>
			  		<td>
			  			<p><span>VDTAmbulatorySummary</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>VDT Inpatient Summary - MU2 170.314 (e)(1)
			  			Inpatient Summary</span></p>
			  		</td>
			  		<td>
			  			<p><span>VDTInpatientSummary</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			 		<td>
			  			<p><span>Non-specific CCDA</span></p>
			  		</td>
			  		<td>
			  			<p><span>NonSpecificCCDA</span></p>
			  		</td>
			 	</tr>
			</table>
  			
  			
  			<div>
  			
  			<br><h3>Example Usage</h3>
  			<p>
  				The following example will validate the file "myfile.xml", with validation type "TransitionsOfCareInpatientSummary":</p>
  			<p >curl -D- -X POST -F &quot;file=@myfile.xml&quot; -F &quot;type_val=TransitionsOfCareInpatientSummary&quot; <%= request.getRequestURL().toString().replace("About", "r1.1/") %></p>
  			
  			</div>
  			
  			
  			
  			<h3><br><br>Validation Response</h3>
  			<div>
Example JSON output:
<code>
<pre>
{
   "performance":{
      "processingTime":"13.014",
      "dateTimeOfRequest":"Fri Aug 22 14:13:03 EDT 2014"
   },
   "report":{
      "docTypeSelected":"Clinical Office Visit Summary",
      "uploadedFileName":"CCDA_Ambulatory.xml",
      "validationResults2":"The file has encountered 1 error(s). The file has encountered 3 warning(s). The file has encountered 1 info message(s).",
      "validationResults1":"Failed Validation",
      "hasErrors":"true",
      "errorCount":1,
      "hasWarnings":"true",
      "warningCount":3,
      "hasInfo":"true",
      "infoCount":1
      
   },
   "errors":[
      {
         "message":"Mu2consol Clinical Office Visit Summary SHALL contain exactly one [1..1] component Contains exactly one [1..1] Consol Instructions Section (templateId: 2.16.840.1.113883.10.20.22.2.45)",
         "source":"MU2 Certification related (cda.mu2consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      }
   ],
   "warnings":[
      {
         "message":"Consol US Realm Header SHALL contain at least one [1..*] recordTarget (CONF:5266) each SHALL contain exactly one [1..1] patientRole, where  (CONF:5268) patient Role SHALL contain exactly one [1..1] patient, where  (CONF:5283) each SHOULD contain zero or more [0..*] languageCommunication, where  (CONF:5406) languageCommunication SHOULD contain zero or one [0..1] proficiencyLevelCode, which SHALL be selected from ValueSet LanguageAbilityProficiency 2.16.840.1.113883.1.11.12199 STATIC (CONF:9965)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument/recordTarget/patientRole/patient/languageCommunication",
         "lineNumber":"129"
      },
      {
         "message":"Consol Provider Organization SHALL contain at least one [1..*] addr with @xsi:type=\"USRealmAddress\" (CONF:5422) addr SHOULD contain zero or one [0..1] @use (CONF:7290), which SHALL be selected from ValueSet PostalAddressUse 2.16.840.1.113883.1.11.10637 STATIC",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument/recordTarget/patientRole/providerOrganization/addr",
         "lineNumber":"140"
      },
      {
         "message":"Consol US Realm Header SHALL contain at least one [1..*] recordTarget (CONF:5266) each SHALL contain exactly one [1..1] patientRole, where  (CONF:5268) each MAY contain zero or one [0..1] providerOrganization, where  (CONF:5416) providerOrganization The id SHOULD include zero or one [0..1] id where id/@root =\"2.16.840.1.113883.4.6\" National Provider Identifier (CONF:9996) (CONF:9996)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument/recordTarget/patientRole/providerOrganization",
         "lineNumber":"136"
      }
   ],
   "info":[
      {
         "message":"Consol US Realm Header MAY contain zero or one [0..1] setId (CONF:5261)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      },
      {
         "message":"Consol US Realm Header MAY contain zero or one [0..1] versionNumber (CONF:5264)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      },
      {
         "message":"Consol US Realm Header MAY contain zero or more [0..*] inFulfillmentOf (CONF:9952)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      },
      {
         "message":"Consol US Realm Header MAY contain zero or more [0..*] authorization (CONF:16792)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      }
   ]
}
</pre>
</code>

</div>


<h2>C-CDA Release 2.0 Validator</h2>
  			
  			<h3>Parameters</h3>
  			
			<p>file = The name of the multipart/form-data parameter that contains attachments must be &quot;file&quot;</p>
  			
  			<div>
  			
  			<br><h3>Example Usage</h3>
  			<p>
  				The following example will validate the file "myfile.xml", with validation type "TransitionsOfCareInpatientSummary":</p>
  			<p >curl -D- -X POST -F &quot;file=@myfile.xml&quot;  <%= request.getRequestURL().toString().replace("About", "r2.0/") %></p>
  			
  			</div>
  			
  			
  			
  			<h3><br><br>Validation Response</h3>
  			<div>
Example JSON output:
<code>
<pre>
{
   "performance":{
      "processingTime":"13.014",
      "dateTimeOfRequest":"Fri Aug 22 14:13:03 EDT 2014"
   },
   "report":{
      "docTypeSelected":"Clinical Office Visit Summary",
      "uploadedFileName":"CCDA_Ambulatory.xml",
      "validationResults2":"The file has encountered 1 error(s). The file has encountered 3 warning(s). The file has encountered 1 info message(s).",
      "validationResults1":"Failed Validation",
      "hasErrors":"true",
      "errorCount":1,
      "hasWarnings":"true",
      "warningCount":3,
      "hasInfo":"true",
      "infoCount":1
      
   },
   "errors":[
      {
         "message":"Mu2consol Clinical Office Visit Summary SHALL contain exactly one [1..1] component Contains exactly one [1..1] Consol Instructions Section (templateId: 2.16.840.1.113883.10.20.22.2.45)",
         "source":"MU2 Certification related (cda.mu2consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      }
   ],
   "warnings":[
      {
         "message":"Consol US Realm Header SHALL contain at least one [1..*] recordTarget (CONF:5266) each SHALL contain exactly one [1..1] patientRole, where  (CONF:5268) patient Role SHALL contain exactly one [1..1] patient, where  (CONF:5283) each SHOULD contain zero or more [0..*] languageCommunication, where  (CONF:5406) languageCommunication SHOULD contain zero or one [0..1] proficiencyLevelCode, which SHALL be selected from ValueSet LanguageAbilityProficiency 2.16.840.1.113883.1.11.12199 STATIC (CONF:9965)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument/recordTarget/patientRole/patient/languageCommunication",
         "lineNumber":"129"
      },
      {
         "message":"Consol Provider Organization SHALL contain at least one [1..*] addr with @xsi:type=\"USRealmAddress\" (CONF:5422) addr SHOULD contain zero or one [0..1] @use (CONF:7290), which SHALL be selected from ValueSet PostalAddressUse 2.16.840.1.113883.1.11.10637 STATIC",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument/recordTarget/patientRole/providerOrganization/addr",
         "lineNumber":"140"
      },
      {
         "message":"Consol US Realm Header SHALL contain at least one [1..*] recordTarget (CONF:5266) each SHALL contain exactly one [1..1] patientRole, where  (CONF:5268) each MAY contain zero or one [0..1] providerOrganization, where  (CONF:5416) providerOrganization The id SHOULD include zero or one [0..1] id where id/@root =\"2.16.840.1.113883.4.6\" National Provider Identifier (CONF:9996) (CONF:9996)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument/recordTarget/patientRole/providerOrganization",
         "lineNumber":"136"
      }
   ],
   "info":[
      {
         "message":"Consol US Realm Header MAY contain zero or one [0..1] setId (CONF:5261)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      },
      {
         "message":"Consol US Realm Header MAY contain zero or one [0..1] versionNumber (CONF:5264)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      },
      {
         "message":"Consol US Realm Header MAY contain zero or more [0..*] inFulfillmentOf (CONF:9952)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      },
      {
         "message":"Consol US Realm Header MAY contain zero or more [0..*] authorization (CONF:16792)",
         "source":"C-CDA Validation related (cda.consol)",
         "path":"/ClinicalDocument",
         "lineNumber":"23"
      }
   ]
}




</div>



		
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 hidden-xs">
		
		<ul class="nav nav-pills nav-stacked">
			<li class="page-header"><h1>Project Links</h1></li>
  			<li><a href="https://github.com/siteadmin/SITE">Project Repository</a></li>
		</ul>
	</div>
</div>



</body>

</html>