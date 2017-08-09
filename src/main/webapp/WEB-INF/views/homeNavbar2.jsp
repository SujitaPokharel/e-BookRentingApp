<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/nav.css" />
<!-- <div id="titlebar">
   <h1> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Online Library Management System </h1>
</div> -->

<%
	String memId = request.getParameter("memId");
%>
<div id="wrap">
	<ul id="nav">
		<li><a href="${context}/issueBook">Issue a book</a>
		<li><a href="${context}/deleteMemberForm">Delete Member
				Profile</a>
		<li><a href="${context}/bookmembers">View Member Names Who
				have been Issued </a>
		<li><a href="${context}">Log Out</a>
	</ul>
</div>
