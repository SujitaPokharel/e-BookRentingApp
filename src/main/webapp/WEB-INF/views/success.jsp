<%@ include file="./include.jsp"%>
<%@ page session="false"%>
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>" />

<html>
<head>
<title>Success</title>
</head>
<body>
	<label>Welcome : <b> ${member.userName}
			${applicationModel.member.userName}</b></label>
	<br />
	<label>You Have Successfully logged in</label>
</body>
</html>

<%@ include file="./homeNavbar2.jsp"%>