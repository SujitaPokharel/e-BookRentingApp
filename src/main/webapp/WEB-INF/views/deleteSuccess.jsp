<%@ include file="./include.jsp"%>
<%@ page session="false"%>
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>" />

<html>
<head>
<title>DeleteSuccess</title>
</head>
<body>
	<label>Success : </label>
	<br />
	<label>You Have Successfully Deleted one entry <b>
			${member.name} </b>
	</label>

	<h2>
		<a href="${context}/success">Home</a>
	</h2>
</body>
</html>
