<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Pet</title>
</head>
<body><h3>Edit Pet</h3>
	<form action = "editPetServlet" method="post">
		Type: <input type ="text" name = "type" value= "${petToEdit.type}">
		Name: <input type ="text" name = "name" value= "${petToEdit.name}">
		Friend: <input type = "text" name = "owner" value= "${petToEdit.owner}">
		<input type = "hidden" name = "id" value="${petToEdit.id}">
		<input type = "submit" value="Save Edited Pet">
	</form>
</body>
</html>