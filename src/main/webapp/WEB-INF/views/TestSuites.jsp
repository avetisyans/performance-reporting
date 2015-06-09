<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Performance</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src=" https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>

<script src="resources/js/helperFunctions.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script>
	$(function() {
		var datepicker = $("#datepicker")
				.datepicker(
						{
							onSelect : function(date) {
								alert("Development is in progress and the action will not take place.");
							}
						});
	});
</script>

<style>
div.ui-datepicker,.ui-datepicker td {
	font-size: 8px;
	size: { height :     100px;
}
}
</style>


</head>
<body onload="environmentName_onClick('${environmentConfigNames[0]}')">

	<c:forEach var="suite" items="${suites}">
		<c:out value="${suite.name}" />
	</c:forEach>

	<div class="well">
		<div class="row">
			<div class="col-sm-3 ">
				<h3>Performance Daily Tests Results</h3>
				<p>
					<font color="red">This is an alpha version with mock data<br>Supported
						browser is Chrome 37 or higher
					</font>
				</p>
			</div>
			<div class="col-sm-9">
				<div class="btn-group">
					<button type="button" data-toggle="dropdown"
						class="btn btn-info dropdown-toggle">
						Products <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="#">vRops 6.0</a></li>
						<li><a href="#">vRops 6.0.1</a></li>
						<li><a href="#">Log Insight 2.4</a></li>
						<li><a href="#">Log Insight 2.5</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- Test results -->
	<div class="container" style="width: 98%">
		<div class="row">
			<div class="col-sm-3">
				<h4 class="sub-header">Test Runs</h4>
				<div class="btn-group">
					<button id="TestRunsButton" type="button" data-toggle="dropdown"
						class="btn btn-info dropdown-toggle">
						<!-- Will be changed -->
						<c:set var="selectedEnvironmentConfigName" scope="session"
							value="${environmentConfigNames[0]}" />
						${environmentConfigNames[0]} <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<c:forEach items="${environmentConfigNames}"
							var="environmentConfigName" varStatus="status">
							<li><a href="#"
								onClick="environmentName_onClick('${environmentConfigName}')">${environmentConfigName}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-sm-1" id="datepickerDiv">
				<div id="datepicker"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3">
				<div class="table-responsive">
					<table id="testRunsTable" class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Date</th>
								<th>Build#</th>
								<th>Executed%</th>
								<th>Pass%</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<!-- table -->

			<div class="col-sm-8">
				<div class="checkbox" id="tableController" tableId="mainTable">
					<c:forEach items="${environmentConfigNames}"
						var="environmentConfigName" varStatus="status">
						<label data-ptcolumn="${environmentConfigName}"
							data-ptcolumnvisible="true"> <input type="checkbox"
							class="checkbox" checked="checked" id="${environmentConfigName}"
							onClick="envCheckBox_onClick(this)"> ${environmentConfigName}
						</label>
					</c:forEach>
				</div>
				<table id="mainTable"
					class="table table-striped table-bordered table-hover">

					<thead>
						<tr>
							<th></th>
							<c:forEach items="${environmentConfigNames}"
								var="environmentConfigName" varStatus="status">
								<th colspan="2" id="${environmentConfigName}">${environmentConfigName}</th>
							</c:forEach>
						</tr>
						<tr>
							<th>Suite Name</th>
							<c:forEach items="${environmentConfigNames}"
								var="testSuiteListPerEnv" varStatus="status">
								<th colspan="1">T/P%/F%</th>
								<th colspan="1">Tag</th>
							</c:forEach>
						</tr>


					</thead>

					<tbody>
						<c:forEach items="${testSuiteNames}" var="testSuiteName">
							<c:if test="${ !empty dailyRunInfosAll}">

								<tr class="info" id="${testSuiteName}">
									<td><a href="#"
										onClick="suitName_onClick('${testSuiteName}')">${testSuiteName}</a></td>
									<c:forEach items="${testSuiteListPerEnvs}"
										var="testSuiteListPerEnv">
										<c:forEach items="${testSuiteListPerEnv.testSuites}"
											var="testSuite" varStatus="testSuiteStatus">
											<c:if test="${testSuite.name == testSuiteName}">
												<!-- Will be changed -->
												<td colspan="1">PASS ${testSuite.id} <input
													type="hidden" name="suiteID" id="suiteID"
													value="${testSuite.id}"> <input type="hidden"
													name="environmentName" id="environmentName"
													value="${testSuiteListPerEnv.environmentConfig.name}">
												</td>
												<td>  ${testSuite.tag} </td>
												<!-- break -->
											</c:if>
										</c:forEach>
									</c:forEach>

								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>

</body>
</html>
