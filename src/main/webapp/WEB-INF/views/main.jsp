<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
	<h1>Patient Medication Schedule Control</h1>


	<form:form method="POST" action="/m7/edit" cssClass="form-group"
		modelAttribute="patientForm">
		<label>Enter patient's name here:</label>
		<form:input class="form-control" path="name" />

		<input class="btn btn-submit" type="submit">

	</form:form>

	<h2>Current schedules</h2>
	<table>
		<tbody>
			<c:forEach items="${patientList}" var="patientName">
				<tr>
					<td>${patientName }</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</t:template>