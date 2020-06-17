<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スケジュール</title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body>
		<c:forEach var="a" items="${list}">
${a.date}:${a.time}:${a.schedule}

	<form method="get" action="Delete">
				<input type="submit" name="button" value="" class="button-delete">
				<input type="hidden" value="${a.id}" name="id">
			</form>

		</c:forEach>

	<p>Please select a time</p>

	<form method="get" action="Insert">
		<select name="hour">
			<c:forEach var="time" items="${hour}">
				<option value="${time}">${time}</option>
			</c:forEach>
		</select> : <select name="minute">
			<c:forEach var="time2" items="${minute}">
				<option value="${time2}">${time2}</option>
			</c:forEach>
		</select><br> <input type="text" name="schedule" value="" />
		<input
			type="submit" name="add" value="add">
	</form>
	<form method="get" action="Return">
		<input type="submit" name="add" value="Return">
	</form>
</body>
</html>