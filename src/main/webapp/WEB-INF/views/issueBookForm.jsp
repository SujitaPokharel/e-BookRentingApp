<%@ include file="./include.jsp"%>
<html>
<head>
<title>List Of Books</title>
<style>
.error {
	font-size: 0.8em;
	color: #ff0000;
}
</style>
</head>

<body>
	<h1>Book list</h1>


	<table BORDER="8" bgcolor="#FAEBD7" cellspacing="10" cellpadding="15">
		<tr>
			<th>Book Name</th>
		</tr>
		<c:forEach var="curBook" items="${bookList}">
			<tr>

				<td>${curBook.title}</td>
				<td><a
					href="${context}/bookIssued?bookId=${curBook.bookid}&memId=1000">Issue</a></td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<br>
	<h2>
		<a href="${context}">Home</a>
	</h2>
</body>
</html>