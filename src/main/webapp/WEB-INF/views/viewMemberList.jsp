<%@ include file="./include.jsp"%>

<html>
<head>
<title>Member Enrollment</title>
</head>
<body>
	<h1>Members who have been Issued ${bookName} book:</h1>
	<table BORDER="8" bgcolor="#FAEBD7" cellspacing="10" cellpadding="15">
		<tr>
			<th>Name</th>
		</tr>

		<c:forEach var="curMember" items="${memberList}">
			<tr>
				<td>${curMember.name}</td>
			</tr>
		</c:forEach>


	</table>

	<br>
	<br>
	<h2>
		<a href="${context}/success">Home</a>
	</h2>
</body>
</html>