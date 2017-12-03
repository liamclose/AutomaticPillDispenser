<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<t:template>
	<div class="col-md-12">
		<h1>Add new Patient</h1>
	</div>
	<div class="col-md-12">
		<form:form method="POST" action="/m7/new" csClass="form-group row"
			modelAttribute="newPatientForm">
			<label>Name:</label>
			<form:input class="form-control" path="name" />
			<label>Room:</label>
			<form:input class="form-control" path="room" />
			<label>id:</label>
			<form:input class="form-control" path="id" />
			<br>
			<div>
				<input type="submit" style="width:" class="form-control" />
			</div>
		</form:form>
	</div>
</t:template>