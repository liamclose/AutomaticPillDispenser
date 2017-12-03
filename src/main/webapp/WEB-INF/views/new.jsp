<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<t:template>
	<div class="col-md-12">
		<h1>Add new Patient</h1>
	</div>
	<form:form method="POST" action="/m7/new" class="row"
		modelAttribute="newPatientForm">
		<div class="col-md-4">
			<label>Name:</label>
			<form:input class="form-control" path="name" />
		</div>
		<div class="col-md-4">
			<label>Room:</label>
			<form:input class="form-control" path="room" />
		</div>
		<div class="col-md-4">
			<label>id:</label>
			<form:input class="form-control" path="id" />
		</div>
		<div class="col-md-4">
			<label>Medication Name:</label>
			<form:input class="form-control" path="medName" />
		</div>
		<div class="col-md-4">
			<label>Dosage</label>
			<form:input class="form-control" path="dosage" />
		</div>
		<div class="col-md-4">
			<label>Medication Id:</label>
			<form:input class="form-control" path="medId" />
		</div>
		<div class="col-md-4" style="margin-top:10px;">
			<input type="submit" style="width:" class="btn btn-default" />
		</div>
	</form:form>

</t:template>