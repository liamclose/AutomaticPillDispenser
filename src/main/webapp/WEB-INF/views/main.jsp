<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dispenser Controls</title>
<<<<<<< HEAD
<link href="resources/css/custom.css" rel="stylesheet">
<link href="resources/css/bootstrap.css" rel="stylesheet">
<script type="resources/js/bootstrap.js"></script>
</head>
<body>
	<h1>Patient Medication Schedule Control</h1>


	<form:form method="POST" action="/m7/edit" cssClass="form-group" modelAttribute="patientForm">
		<label>Enter patient's name here:</label>
		<form:input class="form-control" path="name" />

		<input class="btn btn-submit" type="submit">
=======
<link href="<c:url value='/resources/css/custom.css' />"
	rel="stylesheet">
</head>
<body>
	Controls for the dispenser go here

	<form:form method="POST" action="/m7/edit" modelAttribute="patientForm">
		<label>Enter patient's name here:</label>
		<form:input path="name"/>
			
		<input type="submit">
>>>>>>> 18d9407d977938f07c9b2befeddaaa2e0e2f79c6

	</form:form>
</body>
</html>