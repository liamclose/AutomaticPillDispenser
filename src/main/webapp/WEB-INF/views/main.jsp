<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	<div class="col-12 hidden-sm-down">
		<h1>Patient Medication Schedule Control</h1>
	</div>
	<div class="col-md-6">
		<form:form method="POST" action="/m7/edit" cssClass="form-group"
			modelAttribute="patientSearchForm">
			<div>
				<label>Enter patient's name here:</label>
				<form:input class="form-control" path="id" />
			</div>
			<br> 
			<div>
				<input class="btn btn-submit" type="submit">
			</div>
		</form:form>
	</div>
	<div class="col-md-12">
		<h2>Current schedules</h2>
	</div>

	<div class="col-md-12">
		<table>
			<tbody>
				<c:forEach items="${patientList}" var="patientName">
					<tr>
						<td>${patientName }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:template>
