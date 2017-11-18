<%@ tag description="template for dispenser pages" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dispenser Controls</title>
	<link href="resources/css/custom.css" rel="stylesheet">
	<link href="resources/css/bootstrap.css" rel="stylesheet">
	<script type="resources/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<t:navList/>
		
		<div class="row">
			<div class="col-12"> 
				<jsp:doBody />
			</div>
		</div>
	</div>
</body>
</html>