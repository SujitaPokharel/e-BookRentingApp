<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Find Books Issued</title>
</head>
<body>
	<center>
		<h1>Enter the Member Id to be deleted</h1>
		<form name="GetMemberForm" action="./deleteSuccess.html" method="GET">
			Member Id: <input type="TEXT" name="memberId"> <input
				type="submit" value="Lookup">
		</form>
</body>
</html>