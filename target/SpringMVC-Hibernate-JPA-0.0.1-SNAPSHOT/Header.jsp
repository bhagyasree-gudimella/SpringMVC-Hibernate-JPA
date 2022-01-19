<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	<%@ include file="/WEB-INF/views/css/StyleSheet.css" %>
</style>
<meta charset="UTF-8">
<title>Welcome Page</title>
</head>
<body>
	<h2>Welcome Page</h2>
	<hr>
	<br>
	<table class="center" width=50%>
		<tr>
			<th><a href="/SpringMVC-Hibernate-JPA/Header.jsp">Home</a></th>
			<th><a href="/SpringMVC-Hibernate-JPA/person/personalInfo/addPersonalInfo">Add Person</a></th>
			<th><a href="/SpringMVC-Hibernate-JPA/person/list/personsList">View Persons List</a></th>
			<th><a href="/SpringMVC-Hibernate-JPA/person/personById">View Person By Id</a></th>
			<th><a href="/SpringMVC-Hibernate-JPA/person/emailInfo/askForEmail">Update Person</a></th>
			<th><a href="/SpringMVC-Hibernate-JPA/person/deletePerson/delete">Delete Person</a></th>
		</tr>
	</table>
</body>
</html>