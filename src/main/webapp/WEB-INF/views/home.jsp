<%@ include file="./include.jsp"%>
<%@ page session="false"%>
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>" />
<html>
<head>
<title>Online Library Home Page</title>
<link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />
</head>
<body>
	<%@ include file="./homeNavbar.jsp"%>
	<center>
		<h1>Welcome to Library</h1>
		<img src="${context}/resources/images/libraryBooks.jpg" width="500"
			height="500"> <br> <br> <br>
	</center>
	</div>
</body>
</html>
