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

<portlet:defineObjects />


<div class="panel panel-default" id="smtpStatistics">
    <div class="panel-heading"><h2 class="panel-title">SMTP Statistics</h2></div>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<td style="width:65%"></td>
						<th>Total Count</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>SMTP Searches</th>
						<td>${smtpSearchCountData}</td>
					</tr>
					<tr>
						<th>SMTP Receive Messages</th>
						<td>${smtpReceiveCountData }</td>
					</tr>
				</tbody>
			</table>
		</div>
</div>

	
	
	