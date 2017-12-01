<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template>
	<c:if test="${empty patient}">
		<h1>Search by Patient Id</h1>
		<form:form action="m7/edit" cssClass="form-group"
			modelAttribute="patientForm">
			<form:input class="form-control" path="id" />
			<input type="submit" />
		</form:form>
	</c:if>
	<c:if test="${not empty patient}">
	${patientForm.name }
</c:if>

</t:template>