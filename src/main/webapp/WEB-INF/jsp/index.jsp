<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<hr />

<div>

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<c:forEach items="${testSuites}" var="testSuite">
			<li role="presentation"><a href="#${testSuite.name }"
				aria-controls="home" role="tab" data-toggle="tab">${testSuite.name }</a></li>
		</c:forEach>
		<!-- 	    
	    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
	    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li>
	    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li> -->
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<c:forEach items="${testSuites}" var="testSuite">

			<div role="tabpanel" class="tab-pane" id="${testSuite.name }">
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<th>&nbsp;</th>
							<c:forEach items="${testSuite.testCases}" var="testCase">
								<th>${testCase.name }</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${testSuite.runs}" var="run">
							<tr>
								<td>${run.buildNumber }</td><%-- <c:forEach items="${run.testSuites }" var="testSuite1"><c:forEach items="${testSuite1.testCases}" var="testCase"><c:forEach items="${testCase.env_TestCase_TestResults}" var="env_result"><td>${env_result.testResult.duration }</td></c:forEach></c:forEach></c:forEach> --%>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</c:forEach>
	</div>

</div>

<%-- <hr />hr />
Test Results:
<br />
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Start Time</th>
			<th>End Time</th>
			<th>Duration</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${results}" var="result">
			<tr>
				<td><c:out value="${result.startTime}" /></td>
				<td><c:out value="${result.endTime}" /></td>
				<td><c:out value="${result.duration}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<hr />
<hr />
Runs:
<br />
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
		BuildNumberumber</th>
			<th>Duration</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${runs}" var="run">
			<tr>
				<td><c:out value="$buildNumberumber}" /></td>
				<td><c:out value="${run.duration}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<hr />
<hr />
TestCase is:
<br />


<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Test Case Name</th>
			<th>Result</th>
		Durationation</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${testCase.env_TestCase_TestResults}" var="env_TestCase_TestResults">
			<tr>
				<td><c:out value="${testCase.name}" /></td>
				<td><c:out value="${env_TestCase_TestResults.testReresultesult}" /></td>
				<td><c:out value="${env_TestCase_TestResults.testRedurationation}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</t --%>

