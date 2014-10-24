<% String rootContext = request.getRequestURL().toString().replace("/About", ""); %>

<html>
<head>
	<title>CCDA Validation Services API</title>
	
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

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
					<td>C-CDA Validator</td><td>Version code</td>
				</tr>
				<tr>
					<td>C-CDA Version 1.1</td><td>v1.1</td>
				</tr>
				<tr>
					<td>C-CDA Version 2.0</td><td>v2.0</td>
				</tr>
				<tr>
					<td>Reconciled C-CDA</td><td>Reconciled</td>
				</tr>
				<tr>
					<td>Reference C-CDA</td><td>Reference</td>
				</tr>
				
				<tr>
					<td>Super C-CDA</td><td>Super</td>
				</tr>
				
			</table>
  			<br>
  			<br>
  			<br>
  			<br>
  			<h2>C-CDAVersion 1.1 Validator</h2>
  			
  			<h3>Parameters</h3>
  			
			<p>file = The name of the multipart/form-data parameter that contains attachments must be &quot;file&quot;</p>

			<p>type_val = a parameter indicating the type of document being analyzed</p>
  			
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
  			
  			
  			<h3>Validation Response</h3>
  			<p>
  				The validation response message returns a  JSON object.  The simple object only includes a boolean attribute named "result".  If the validation passes, and the code exists in the specified vocabulary, "result" will be true.  If the validation fails and the code could not be found in the specified vocabulary, "result" will be false.
  			</p>
  			
  			<h2>Example Usage</h2>
  			<p>
  				The following example will validate the code value of "C-D2223", from the "SNOWMED" vocabulary value set.
  			</p>
  			<p>	
  				<%= rootContext %>/validateCode/SNOMED/C-D2223
  			</p>		
		</div>

		
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 hidden-xs">
		<ul class="nav nav-pills nav-stacked">
			<li class="page-header"><h1>Service API</h1></li>
  			<li><a href="#validateCode">Validate Code</a></li>
  			<li><a href="#validateName">Validate Display Name</a></li>
		</ul>
		<ul class="nav nav-pills nav-stacked">
			<li class="page-header"><h1>Project Links</h1></li>
  			<li><a href="https://github.com/">Project Repository</a></li>
		</ul>
	</div>
</div>



</body>

</html>