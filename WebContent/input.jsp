<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>カレンダー</title>
<link rel="stylesheet" type="text/css" href="top.css">
</head>
<body>
	<div>
		<form method="get" action="calendar">
			<p>${message}</p>
			<input type="text" name="year" value="" placeholder=year><br>
			<input type="text" name="month" value="" placeholder=month><br>
			<input class="submit" type="submit" name="submit" value="NEXT" />
		</form>
	</div>
</body>
</html>