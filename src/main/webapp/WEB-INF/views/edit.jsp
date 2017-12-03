<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template>

	<c:if test="${empty patient}">
		<div class="col-md-12">
			<h1>Search by Patient Id</h1>
		</div>
		<div class="col-md-6">
			<form:form action="m7/edit" cssClass="form-group"
				modelAttribute="patientSearchForm">
				<form:input class="form-control" path="id" />
				<input type="submit" class="btn btn-default" />
			</form:form>
		</div>
	</c:if>
	<c:if test="${not empty patient}">
		<div class="col-md-12">${patient.name }</div>
	</c:if>
</t:template>