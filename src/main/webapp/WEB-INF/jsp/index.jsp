<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

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
			<th>Start Time</th>
			<th>End Time</th>
			<th>Duration</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${runs}" var="run">
			<tr>
				<td><c:out value="${run.startTime}" /></td>
				<td><c:out value="${run.endTime}" /></td>
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
			<th>Start Time</th>
			<th>End Time</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${testCase.env_TestCase_TestResults}" var="env_TestCase_TestResults">
			<tr>
				<td><c:out value="${testCase.name}" /></td>
				<td><c:out value="${env_TestCase_TestResults.testResult.startTime}" /></td>
				<td><c:out value="${env_TestCase_TestResults.testResult.endTime}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

