<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	Patient display page
	
	
	
	
	<div class=row>
		<div class="col-md-6">name: ${patient.name }</div>
		<div class="col-md-6">room: ${patient.room }</div>
	</div>


</t:template>