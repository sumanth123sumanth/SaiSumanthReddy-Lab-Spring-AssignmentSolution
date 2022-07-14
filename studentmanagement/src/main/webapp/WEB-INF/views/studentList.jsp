<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- Stylesheet (CSS) & JSP Tag
     Library -->
<!DOCTYPE html>
<html>
<head>
<title>Student List</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3> Students Directory</h3>
		<a href="/StudentManagement/students/add" class="btn btn-primary btn-sm mb-3">Add Student</a>
		<a href="/StudentManagement/logout"
				class="btn btn-primary btn-sm mb-3 mx-auto"> Logout </a>
		<table class="table table-bordered table-striped">
			<thead  class="thead-dark">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Course</th>
					<th>Country</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentModel}" var="student">
					<tr>
					<td><c:out value="${student.firstname}"></c:out></td>
					<td><c:out value="${student.lastname}"></c:out></td>
					<td><c:out value="${student.course}"></c:out></td>
					<td><c:out value="${student.country}"></c:out></td>
					<td>
					<a href="/StudentManagement/students/update?id=${student.id}" class="btn btn-info btn-sm">Update</a>
					<a href="/StudentManagement/students/delete?id=${student.id}" class="btn btn-danger btn-sm" onclick="if(!(confirm('Are you sure to delete this student?'))) return false">Delete</a>
					</td>
					</tr>
				</c:forEach>
				<!-- Tag lib For loop -->
			</tbody>
		</table>
	</div>
</body>
</html>