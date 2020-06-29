<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スケジュール</title>
<link rel="stylesheet" type="text/css" href="Style.css">
<script type="text/javascript" src="edit.js"></script>
</head>
<body>
	<div class="ooo">
		<c:forEach var="a" items="${list}" varStatus="status">
			<p>${a.date}&ensp;${a.time}</p>
			<p class="a" id="a_${status.count}">${a.schedule}</p>
			<form method="post" action="Edit">
				<input type="text" value="${a.schedule}" id="p1_${status.count}"
					class="edittext" name="update"> <input type="hidden"
					value="${a.id}" name="id"> <input type="submit"
					value="update" id="update_${status.count}" class="edittext">
			</form>

			<form method="post" action="Delete" class="delete">
				<input type="submit" name="button" value="" class="button-delete">
				<input type="hidden" value="${a.id}" name="id">
			</form>
			<input type="button" value="edit" id="edit_${status.count}" class="edit"
				onclick="OnButtonClick(event)">
		</c:forEach>

		<br>
		<p>Please select a time</p>

		<form method="post" action="Insert">
			<select name="hour">
				<c:forEach var="time" items="${hour}">
					<option value="${time}">${time}</option>
				</c:forEach>
			</select> : <select name="minute">
				<c:forEach var="time2" items="${minute}">
					<option value="${time2}">${time2}</option>
				</c:forEach>
			</select><br> <input class="bottan2" type="text" name="schedule" value="" />
			<input class="bottan" type="submit" name="add" value="add">
		</form>
		<form method="get" action="Return">
			<input class="bottan" type="submit" name="add" value="Return">
		</form>
	</div>
</body>
</html>