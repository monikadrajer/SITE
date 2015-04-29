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
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<portlet:defineObjects />

<portlet:actionURL var="smtpReceiveWeeklyCounts" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="smtpReceiveWeeklyCounts"/>
</portlet:actionURL>

<portlet:actionURL var="smtpSendWeeklyCounts" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="javax.portlet.action" value="smtpSendWeeklyCounts"/>
</portlet:actionURL>

<script>
	
	$(function() {
		
		d3.csv("${smtpSearchWeeklyCounts}").get().on("load", function(data) {
			loadStatistics(data, "SMTP Search Weekly Statistics", "#canvas-svg-smtpsearch", 330, 300);
			d3.csv("${smtpReceiveWeeklyCounts}").get(function(error,data) {
				loadStatistics(data, "SMTP Receive Weekly Statistics", "#canvas-svg-smtpreceive", 330, 300, true);
			});
		});

		
	});

</script>
		
<div class="row well">
	<!--div class="col-md-6" style="text-align: center;">
		<h2>${successfulCcdas}</h2>
		<p>c-cdas passed</p>
	</div>
	<div class="col-md-6" style="text-align: center;">
		<h2>${failedCcdas}</h2>
		<p>c-cdas failed</p>
	</div-->
	<div id="canvas-svg-smtpsearch"></div>
</div>
<div class="row well">
	<div id="canvas-svg-smtpreceive"></div>
</div>
<% 
	if (renderRequest.isUserInRole("administrator")) {
	%>
	<div style="width:100%">
		<a class="btn btn-success" href="statistics"  style="width: 100%;">See More Stats</a>
	</div>
	<%
	}
	%>