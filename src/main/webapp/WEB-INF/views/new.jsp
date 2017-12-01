<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<t:template>

	add new patient
	<form:form method="POST" action="/m7/new" csClass="form-group row"
		modelAttribute="newPatientForm">
		
		<div class="col-md-12">
			<label>Name:</label>
			<form:input class="form-control" path="name"/>
		</div>
		<div class="col-md-12">
			<label>Room:</label>
			<form:input class="form-control" path="room"/>
		</div>
		<div class="col-md-12">
			<label>id:</label>
			<form:input class="form-control" path="id"/>
		</div>
		<input type="submit" class="form-control"/>
	</form:form>



</t:template>