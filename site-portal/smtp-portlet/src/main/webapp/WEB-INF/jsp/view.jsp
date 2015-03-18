<%
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
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="javax.portlet.PortletPreferences"%>
<%@ page import="com.liferay.util.PwdGenerator"%>
<%@ page import="com.liferay.portal.service.PortletPreferencesLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<portlet:defineObjects />

<portlet:actionURL var="sampleCCDATree" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="sampleCCDATree"/>
</portlet:actionURL>

<portlet:resourceURL id="getTrustBundle" var="getTrustBundleResource"/>

<portlet:actionURL var="uploadTrustAnchor" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="uploadTrustAnchor"/>
</portlet:actionURL>

<portlet:actionURL var="uploadCCDADirectEdgeReceive" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="uploadCCDADirectEdgeReceive"/>
</portlet:actionURL>

<portlet:actionURL var="precannedCCDADirectEdgeReceive" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="precannedCCDADirectEdgeReceive"/>
</portlet:actionURL>

<portlet:actionURL var="smtpSearch" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="smtpSearch"/>
</portlet:actionURL>

<portlet:renderURL var="endpointcerturl"> 
	<portlet:param name="mvcPath" value="/Certificates/PublicKeys/direct.sitenv.org_ca.der" />  
</portlet:renderURL>  

<script type="text/javascript">
	window.currentContextPath = "<%=request.getContextPath()%>";
	var sampleCCDATreeURL = '${sampleCCDATree}';
</script>

<div class="panel panel-default" id="smtpsearchwidget">
	<div class="panel-heading"><h3 class="panel-title">Validate messages sent via SMTP are being received</h3></div>
	<div class="panel-body">
			In order to verify your messages sent via SMTP are being received by SITE
			<ul>
				<li>Send SMTP messages from your implementation to:
					<ul>
						<li>
							provider1@edgedirect.sitenv.org
						</li>			
					</ul>
				</li>
				<li>Search messages received by site using the 'FROM' address that represents a message from your Direct Edge system</li>
			</ul>
		<br/>
		<div class="well">
			<form id="smtpsearchform"  action="${smtpSearch}" method="POST">
			<label for="requestListGrouping">Enter address to search:</label>
				<input type="text" name="smtpsearchinput" id="smtpsearchinput" class="validate[custom[email]] form-control" tabindex="1"/>
				
			<hr />
				<button id="smtpsearchsubmit" type="submit"
					class="btn btn-primary start" onclick="return false;"  tabindex="1">
					<i class="glyphicon glyphicon-search"></i> <span>Lookup Messages</span>
				</button>	
			</form>
		</div>
	</div>
</div>

<div class="panel panel-default" id="directreceivewidget">
      <div class="panel-heading"><h3 class="panel-title">Send SMTP message to the test tool</h3></div>
  		<div class="panel-body">
			<p>
				You can use this tool to send an SMTP message to the Direct Edge Test Tool per the below instructions. Or you can send an SMTP message to provider1@edge.direct.sitenv.org from your system.
				<ul>
					<li>
						<u>Enter your from address:</u> The email address that represents a message from your Direct Edge system.
					</li>
					<li>
						<u>Choose your own content:</u> Developers can use their own files as the payload of the Direct message sent from the Sandbox. 
									This provides the ability to verify the file they chosen and that the contents were decrypted appropriately.
					</li>
					<li>
						<u>Choose pre-canned content:</u> Provides a list of files that you can choose from as the payload of the Direct message.
					</li>
					<li>
						Once the above fields are populated, hit the send message button.<br/>
						You can validate the message was received by using the lookup tool in the above form.		
					</li>
				</ul>
			</p>
			<br/><br/>
			<!-- Nav tabs -->
			<ul id="directMessageType" class="nav nav-tabs" role="tablist">
			  <li class="active"><a href="#precanned" role="tab" data-toggle="tab"  tabindex="1">Choose Precanned Content</a></li>
			  <li><a href="#choosecontent" role="tab" data-toggle="tab"  tabindex="1">Choose Your Own Content</a></li>
			</ul>
			<div class="well">
			
			
		
		<div class="tab-content">
  			<div class="tab-pane active" id="precanned">
  				<div id="precannedFormWrapper">
			<form id="precannedForm"  action="${precannedCCDADirectEdgeReceive}" method="POST">
				<p>
				<label for="fromemail">Enter From Address:</label><br/>
					<input id="fromemail"
						class="validate[required,custom[email]] form-control" 
						data-errormessage-value-missing="end point is required!"
						data-errormessage-custom-error="end point format is invalid (hint:example@test.org)"
						name="fromemail"
						placeholder="from email address"
						style="display: inline;" type="text"  tabindex="1"/>
				</p>
				
				<br />
				<noscript><input type="hidden" name="redirect" value="true"  /></noscript>
				<div id="precannederrorlock" style="position: relative;">
					<div class="row">
					<div class="col-md-12">
					<label for="dLabel">Select a Precanned Sample C-CDA File to Send:</label><br/>
									<div class="dropdown">
										<button id="dLabel" data-toggle="dropdown"
											class="btn btn-success dropdown-toggle validate[funcCall[precannedRequired]]" type="button" 	tabindex="1">
											Pick Sample <i class="glyphicon glyphicon-play"></i>
										</button>

										<ul class="dropdown-menu rightMenu" role="menu" aria-labelledby="dLabel" style="overflow: scroll; /* position: absolute; */" >
											<li>
												<div id="ccdafiletreepanel"></div>
											</li>
										</ul>
									</div>
									<div>
									<span id="precannedfilePathOutput"></span>
									</div>
					</div>
					</div>
				</div>
				<hr />
				<button id="precannedCCDAsubmit" type="submit"
					class="btn btn-primary start" onclick="return false;"  tabindex="1">
					<i class="glyphicon glyphicon-envelope"></i> <span>Send
						Message</span>
				</button>
				<input id="precannedfilepath"
						name="precannedfilepath" type="hidden">
			</form>
		</div>
  			</div>
  			<div class="tab-pane" id="choosecontent">
  			<div id="uploadFormWrapper">
			<form id="ccdauploadform" action="${uploadCCDADirectEdgeReceive}" method="POST" enctype="multipart/form-data">
				<p>
					<label for="ccdauploademail">Enter Your Endpoint Name:</label><br/>
					 <input id="ccdauploademail"
						class="validate[required,custom[email]] form-control"
						data-errormessage-value-missing="end point is required!"
						data-errormessage-custom-error="end point format is invalid (hint:example@test.org)"
						name="ccdauploademail"
						placeholder="recipient direct email address"
						style="display: inline;" type="text"  tabindex="1"/>
				</p>
				<br />
				<noscript><input type="hidden" name="redirect" value="true" /></noscript>
				<div id="ccdauploaderrorlock" style="position: relative;">
					<div class="row">
					<div class="col-md-12">
						<label for="ccdauploadfile">Select a Local C-CDA File to Send:</label><br/>
							<span
								class="btn btn-success fileinput-button" id="ccdauploadfile-btn"> <i
									class="glyphicon glyphicon-plus"></i>&nbsp;<span>Upload C-CDA</span> <!-- The file input field used as target for the file upload widget -->
									<input id="ccdauploadfile" type="file"
									name="ccdauploadfile" class="validate[required, custom[maxCCDAFileSize]]"  tabindex="1"/>
							</span>
							
								<div id="ccdauploadfiles" class="files"></div>
					</div>
					</div>
				</div>
				<hr />
				<button id="ccdauploadsubmit" type="submit"
					class="btn btn-primary start" onclick="return false;"  tabindex="1">
					<i class="glyphicon glyphicon-envelope"></i> <span>Send Message</span>
				</button>
			</form>
		</div>
  			</div>
		</div>
		
		</div>
		<br/>
			
			<div class="clear"></div>
		</div>
</div>

<div class="modal modal-wide fade" id="resultsDialog" tabindex="-1"
	role="dialog" aria-labelledby="resultModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4>SMTP Search Results</h4>
			</div>
			<div class="modal-body" id="SMTPSearchResult"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close Search Results</button>
			</div>
		</div>
	</div>
</div>

