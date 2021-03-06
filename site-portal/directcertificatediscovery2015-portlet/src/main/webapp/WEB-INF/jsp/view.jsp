<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />

<portlet:resourceURL var="queryDcdtHosting" id="queryDcdtHosting"></portlet:resourceURL>
<portlet:resourceURL var="queryDcdtEmailMapping"
	id="queryDcdtEmailMapping"></portlet:resourceURL>

<script type="text/javascript">

	var URL_HOSTING_PROCESS = "<%=queryDcdtHosting%>";
	var URL_DISCOVERY_MAIL_MAPPING_ADD = "<%=queryDcdtEmailMapping%>";

</script>
<div class="panel panel-default" id="panel_hosting">
	<div class="panel-heading">
		<h2 class="panel-title">Hosting - Verify your certificate can be
			discovered</h2>
	</div>
	<div class="panel-body">
		<div class="directions">Directions</div>
		<p>Step 1: Determine the required test cases for your SUT (System
			Under Test). Notice that there are two options for storage of
			address-bound and domain-bound certificates.</p>
		<p>Step 2: Select a test case that reflects the SUT from the
			dropdown.</p>
		<p>Step 3: Read the Description and Instructions for the selected
			test case. Then enter the Direct address and submit. Your SUT
			configuration may require that you select more than one test case. If
			so, then select one test case at a time, following the instructions
			to execute the test after each selection.</p>
		<div class="well">
			<form id="form-testcases-hosting" enctype="multipart/form-data"
				name="form-testcases-hosting" action="about:blank" method="post"
				onsubmit="return false;">
				<div id="testcase-info" class="input-group-small">
					<div class="form-group form-group-addons">
						<div class="has-error">
							<div
								class="input-group-addon input-group-addon-msgs input-group-addon-msgs-global">
								<span class="glyphicon glyphicon-exclamation-sign"></span> <strong>Invalid
									Hosting testcase submission</strong>:
								<ul></ul>
							</div>
						</div>
					</div>
					<p>
					<div class="form-group">
						<label for="testcase-hosting-select">Select a Hosting Test
							Case:</label> <select id="testcase-hosting-select"
							name="testcase-hosting-select"
							class="form-control" tabindex="1"
							data-parsley-required data-parsley-required-message="This field is required.">
							<option value="">-- No testcase selected --</option>
							<option value="H1_DNS_AB_Normal">H1 - Normal
								address-bound certificate search in DNS</option>
							<option value="H2_DNS_DB_Normal">H2 - Normal
								domain-bound certificate search in DNS</option>
							<option value="H3_LDAP_AB_Normal">H3 - Normal
								address-bound certificate search in LDAP</option>
							<option value="H4_LDAP_DB_Normal">H4 - Normal
								domain-bound certificate search in LDAP</option>
						</select>
					</p>
					
					<div class="infoArea"></div>
					
					</div>
					<!-- Description of the test case will go here: -->
					<div id="hosting-testcase-desc" class="testcase hide"
						aria-hidden="true">Please select a test case.</div>
						
					<div class="form-group">
						<p>
							<label for="testcase-hosting-direct-addr">Enter Your
								Direct Address:</label> <input id="testcase-hosting-direct-addr"
								class="form-control" name="directAddress"
								placeholder="direct email address" style="display: inline;"
								type="email" tabindex="1" disabled="disabled" data-parsley-required data-parsley-required-message="This field is required." />
						</p>
						<div class="infoArea"></div>
					</div>

					<hr />
					<div class="form-group form-group-buttons">
						<span class="btn-group btn-group-sm">
							<button type="button" class="btn btn-primary start" tabindex="1"
								id="testcase-hosting-submit">
								<i class="glyphicon glyphicon-ok"></i> <span>Submit</span>
							</button>

							<button type="reset" class="btn btn-default start" tabindex="1"
								id="testcase-hosting-reset">
								<i class="glyphicon glyphicon-refresh"></i> <span>Reset</span>
							</button>
							
						</span>
					</div>

				</div>
				<div id="testcase-results" class="input-group-small hide"
					aria-hidden="true">
					<div class="form-group">
						<div class="directions">
							<span class="form-cell form-cell-label">
									<span
									class="glyphicon glyphicon-certificate glyphicon-type-info"></span>Results
							</span>
						</div>
					</div>
					<div id="testcase-results-accordion"></div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="panel panel-default" id="panel_discovery">
	<div class="panel-heading">
		<h2 class="panel-title">Discover DCDT's Certificates</h2>
	</div>
	<div class="panel-body">
		<div class="directions">Directions</div>
		<p>Step 1: Download the Testing Tool's trust anchor.</p>
		<p style="margin-left: 15px;">
			<a href="http://demo31.direct-test.com/dcdt-web/discovery/anchor"
				target="_blank" tabindex="1">Download Trust Anchor</a>
		</p>
		<p>Step 2: Upload the anchor to your Direct instance. This will
			allow you to send messages to our tool.</p>
		<p>Step 3: Using the form below, map the Direct email address from
			which you will be sending messages to a non-Direct email address that
			will receive a regular email containing test results. This email
			address should be able to receive plain text messages. Make sure you
			have access to the recipient email address in order to verify the
			receipt of the messages.</p>
		<div class="well">
			<form id="form-testcases-discovery-mail-mapping" action="about:blank"
				method="POST" enctype="multipart/form-data" target="_self"
				onsubmit="return false;"
				name="form-testcases-discovery-mail-mapping">

				<div class="form-group">
					<div>
						<label for="directAddress">Enter Your Direct Address:</label><br/>
						<input id="directAddress" class="form-control"
							name="directAddress" placeholder="direct email address"
							style="display: inline;" type="email" tabindex="1" data-parsley-required data-parsley-required-message="This field is required."  />
					</div>
					<div class="infoArea"></div>
				</div>
				<div class="form-group">
					<div>
						<label for="resultsAddress">Enter Your Email Address (for
							results):</label> <input id="resultsAddress" class="form-control"
							name="resultsAddress" placeholder="results email address"
							style="display: inline;" type="email" tabindex="1" data-parsley-required data-parsley-required-message="This field is required."  />
					</div>
					<div class="infoArea"></div>
				</div>

				<hr />
				<div class="form-group form-group-buttons">
					<button id="discovery-mail-mapping-submit" type="button"
						class="btn btn-primary start" tabindex="1">
						<i class="glyphicon glyphicon-ok"></i> <span>Submit</span>
					</button>
					<button id="discovery-mail-mapping-reset" type="reset"
						class="btn btn-default start" tabindex="1">
						<i class="glyphicon glyphicon-refresh"></i> <span>Reset</span>
					</button>
				</div>
				<div class="input-group-small">
					<div class="form-group form-group-addons">
						<div class="has-error">
							<div
								class="input-group-addon input-group-addon-msgs input-group-addon-msgs-global">
								<span class="glyphicon glyphicon-exclamation-sign"></span> <strong>Invalid
									Discovery mail mapping</strong>:
								<ul></ul>
							</div>
						</div>
						<div class="has-success">
							<div
								class="input-group-addon input-group-addon-msgs input-group-addon-msgs-global">
								<span class="glyphicon glyphicon-ok-sign"></span> <strong>Discovery
									mail mapping modified</strong>:
								<ul></ul>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<p>Step 4: Choose a test case from the drop down menu below. Read
			the test case description below the "Direct Address" field, copy the
			displayed Direct address and proceed to step 5. You should run all of
			the tests in order to verify that your system can correctly discover
			certificates in either DNS CERT records or LDAP servers. (Note: your
			system MUST NOT already contain a certificate for the address
			selected or the test case will not be valid).</p>
		<p>Step 5: Attempt to send a message to the Direct address that
			you've just copied. Please only send to one address at a time. The
			test case results message will indicate the test case results. See
			the test case instructions for additional information.</p>
		<div class="well">
			<form id="form-testcases-discovery" action="about:blank"
				method="POST" target="_self" enctype="multipart/form-data"
				name="form-testcases-discovery">
				<label for="testcase-select">Select a Discovery Test Case:</label><br />
				<select id="testcase-select" name="testcase-select"
					class="form-control" tabindex="1">
					<option value="">-- No testcase selected --</option>
					<option value="D1_DNS_AB_Valid">D1 - Valid address-bound
						certificate discovery in DNS</option>
					<option value="D2_DNS_DB_Valid">D2 - Valid domain-bound
						certificate discovery in DNS</option>
					<option value="D3_LDAP_AB_Valid">D3 - Valid address-bound
						certificate discovery in LDAP</option>
					<option value="D4_LDAP_DB_Valid">D4 - Valid domain-bound
						certificate discovery in LDAP</option>
					<option value="D5_DNS_AB_Invalid">D5 - Invalid
						address-bound certificate discovery in DNS</option>
					<option value="D6_DNS_DB_Invalid">D6 - Invalid
						domain-bound certificate discovery in DNS</option>
					<option value="D7_LDAP_AB_Invalid">D7 - Invalid
						address-bound certificate discovery in LDAP</option>
					<option value="D8_LDAP_DB_Invalid">D8 - Invalid
						domain-bound certificate discovery in LDAP</option>
					<option value="D9_DNS_AB_SelectValid">D9 - Select valid
						address-bound certificate over invalid certificate in DNS</option>
					<option value="D10_LDAP_AB_UnavailableLDAPServer">D10 -
						Certificate discovery in LDAP with one unavailable LDAP server</option>
					<option value="D11_DNS_NB_NoDNSCertsorSRV">D11 - No
						certificates discovered in DNS CERT records and no SRV records</option>
					<option value="D12_LDAP_NB_UnavailableLDAPServer">D12 - No
						certificates found in DNS CERT records and no available LDAP
						servers</option>
					<option value="D13_LDAP_NB_NoCerts">D13 - No certificates
						discovered in DNS CERT records or LDAP servers</option>
					<option value="D14_DNS_AB_TCPLargeCert">D14 - Discovery of
						certificate larger than 512 bytes in DNS</option>
					<option value="D15_LDAP_AB_SRVPriority">D15 - Certificate
						discovery in LDAP based on SRV priority value</option>
					<option value="D16_LDAP_AB_SRVWeight">D16 - Certificate
						discovery in LDAP based on SRV weight value</option>
					<option value="D17_DNS_AB_CRLRevocation">D17 - CRL-based 
						revocation checking for address-bound certificate discovery in DNS</option>
					<option value="D18_DNS_AB_AIAIntermediateIssuer">D18 - AIA-based intermediate 
						issuer certificate retrieval for address-bound certificate discovery in DNS</option>
				</select> <br />
				<div id="testcase-discovery-direct-addr">
					<span class="glyphicon glyphicon-envelope glyphicon-type-info"></span>
					<strong>Direct Address</strong>: <span></span>
				</div>
				<div id="testcase-desc" class="testcase hide" aria-hidden="true">Please
					select a test case.</div>
			</form>
		</div>
	</div>
</div>