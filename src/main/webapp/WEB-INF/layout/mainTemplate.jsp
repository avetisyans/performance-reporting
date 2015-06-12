<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="taglib.jsp"%>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>
	<div class="container">
		<h1>Some Header</h1>
		
		<tiles:insertAttribute name="body" />
		
		<br /><br /><br /><br />
		<div style="text-align: center">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>