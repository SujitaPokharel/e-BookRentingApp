<%@ include file="./include.jsp"%>

<html>
<head>
<title>Login Page</title>
</head>

<body>
	<center>
		<h3>Login Page</h3>

		<form:form name="login" action="./login" commandName="loginDetails"
			method="POST">

			<table>
				<tr>
					<td colspan="3"><h3>Spring MVC Login</h3></td>
				</tr>
				<tr>
					<td><label>UserName</label></td>
					<td><form:input path="userName"></form:input></td>
					<td><font color="red"><form:errors path="userName"></form:errors></font></td>
				</tr>
				<tr>
					<td><label>Password</label></td>
					<td><form:input path="password" type="password"></form:input></td>
					<td><font color="red"><form:errors path="password"></form:errors></font></td>
				</tr>
				<tr>
					<td><a href="/libraryapp/">Back</a></td>
					<td><input type="submit"
						value="<fmt:message key="enterBtn" />"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><font color="red"><form:errors /></font></td>
				</tr>
			</table>

		</form:form>
</body>
</html>





