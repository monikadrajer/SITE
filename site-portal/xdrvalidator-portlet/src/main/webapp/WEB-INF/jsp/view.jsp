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

<portlet:actionURL var="urlAction" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="uploadXDR"/>
</portlet:actionURL>

<portlet:actionURL var="precannedXDR" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="precannedXDR"/>
</portlet:actionURL>

<portlet:actionURL var="sampleCCDATree" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="sampleCCDATree"/>
</portlet:actionURL>

<portlet:actionURL var="xdrSendGetRequestList" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="xdrSendGetRequestList"/>
</portlet:actionURL>

<portlet:actionURL var="xdrSendGetRequest" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="xdrSendGetRequest"/>
</portlet:actionURL>

<script type="text/javascript">
	var url = '${urlAction}';
	var precannedUrl = '${precannedXDR}';
	window.currentContextPath = "<%=request.getContextPath()%>";
	var sampleCCDATreeURL = '${sampleCCDATree}';
	var xdrSendGetRequestList = '${xdrSendGetRequestList}';
	var xdrSendGetRequest = '${xdrSendGetRequest}';
</script>


<div class="panel panel-default" id="xdrsendwidget">
	<div class="panel-heading"><h3 class="panel-title">XDR Send</h3></div>
	<div class="panel-body">
		<p>
			Send messages from your implementation to the endpoint listed below:
			<ul>
				<li>
					${xdrSoapEndpoint}
				</li>			
			</ul>
		</p>
		<div class="well">
			<form id="XDRSendGetRequestList"  action="${xdrSendGetRequestList}" method="POST">
			<label for="requestListGrouping">Enter the XDR Request "From Address":</label>
				<input type="text" name="requestListGrouping" id="requestListGrouping" class="validate[required] form-control" tabindex="1"/>
				<span style="font-size:smaller;">Having trouble finding your requests? Perhaps the "From Address" element was not populated correctly.  Try entering the external IP address of the sender.</span>
			<hr />
				<button id="xdrSendSearchSubmit" type="submit"
					class="btn btn-primary start" onclick="return false;"  tabindex="1">
					<i class="glyphicon glyphicon-search"></i> <span>Lookup Requests</span>
				</button>	
			</form>
		</div>
	</div>
</div>

<div class="modal modal-wide fade" id="xdrSendModal" tabindex="-1" role="dialog" aria-labelledby="xdrSendModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<ul class="nav nav-tabs" id="resultModalTabs">
					  <li><a href="#tabs-1" data-toggle="tab">XDR Send Requests</a></li>
					</ul>
			</div>
			<div class="modal-body">
				<div id="ValidationResult">
					<div class="tab-content" id="resultTabContent">
						<div class="tab-pane" id="tabs-1">
							<h2>Content heading 1</h2>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" id="closeResultsBtn" data-dismiss="modal">Close Results</button>
			</div>
		</div>
	</div>
</div>

<div class="panel panel-default" id="xdrreceivewidget">
      <div class="panel-heading"><h3 class="panel-title">XDR Receive</h3></div>
  		<div class="panel-body">
			<p>
				Receive messages from the Sandbox to your system. 
			</p>
			<br/><br/>
			<ul id="xdrMessageType" class="nav nav-tabs" role="tablist">
			  <li class="active"><a href="#precanned" role="tab" data-toggle="tab"  tabindex="1">Choose Precanned Content</a></li>
			  <li><a href="#choosecontent" role="tab" data-toggle="tab"  tabindex="1">Choose Your Own Content</a></li>
			</ul>
			<div class="well">
					<div class="tab-content">
  			<div class="tab-pane active" id="precanned">
  				<div id="precannedFormWrapper">
			<form id="XDRPrecannedForm"  action="${precannedXDR}" method="POST">
				
				<label for="precannedWsdlLocation">Enter Your Endpoint URL:</label>
				<input type="text" name="precannedWsdlLocation" id="precannedWsdlLocation" class="validate[required,custom[url]] form-control" tabindex="1"/>
				
				<br />
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
			<form id="XDRValidationForm" action="${urlAction}" method="POST" enctype="multipart/form-data">
      	
			
				<label for="wsdlLocation">Enter Your Endpoint URL:</label>
				<input type="text" name="wsdlLocation" id="wsdlLocation" class="validate[required,custom[url]] form-control" tabindex="1"/>
				
				
				<br/><br/>
			
			<noscript><input type="hidden" name="redirect" value="true" /></noscript>
			<div id="ccdauploaderrorlock" style="position: relative;">
				<div class="row">
					<div class="col-md-12">
						<label for="fileupload">Select a Local C-CDA File to Send:</label><br/>
						<span class="btn btn-success fileinput-button" id="fileupload-btn"> <i
								class="glyphicon glyphicon-plus"></i>&nbsp;<span>Upload C-CDA...</span>
								<input id="fileupload" type="file" name="file"  class="validate[required, custom[xmlfileextension[xml|XML]], custom[maxCCDAFileSize]]"  tabindex="1"/>
						</span>
						<div id="files"></div>
						
					</div>
					
					
				</div>
					
					
			</div>
			<hr/>
			<button id="formSubmit" type="submit" class="btn btn-primary start" onclick="return false;"  tabindex="1">
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



