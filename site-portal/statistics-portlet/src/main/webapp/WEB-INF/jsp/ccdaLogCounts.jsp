<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<portlet:defineObjects />



<div class="panel panel-default" id="ccdaStatistics">
    <div class="panel-heading"><h2 class="panel-title">C-CDA Statistics</h2></div>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<td style="width:65%"></td>
						<th>Total Count</th>
					</tr>
				</thread>
				<tbody>
					<tr>
						<th>C-CDA Validations</th>
						<td>${ccdaLogCountData }</td>
					</tr>
				</tbody>
			</table>
		</div>
</div>