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
<div>
		<c:forEach var="a" items="${list}">
<p>${a.date}     ${a.time}</p><p class="a">${a.schedule}</p>

	<form method="get" action="Delete" class="delete">
				<input type="submit" name="button" value="" class="button-delete">
				<input type="hidden" value="${a.id}" name="id">
			</form>
			<form method="get" action="edit">
			<input type="submit" name="button" value="edit" class="button-edit">
				<input type="hidden" value="${a.id}" name="id">
			</form>

		</c:forEach>
<br>
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
		</select><br> <input class="bottan2" type="text" name="schedule" value="" />
		<input
			class="bottan" type="submit" name="add" value="add">
	</form>
	<form method="get" action="Return">
		<input class="bottan" type="submit" name="add" value="Return">
	</form>
	</div>
</body>
</html>