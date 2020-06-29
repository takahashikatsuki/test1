<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>カレンダー</title>
<link rel="stylesheet" type="text/css" href="calendar.css">
</head>
<body>
	<div>
		${YEAR}/${MONTH} <br>
		<table>
			<tr>
				<c:forEach var="var1" items="${space1}">
				${var1}
</c:forEach>
			</tr>
			<form method="post" action="Time">
				<input type="hidden" value="${YEAR}" name="year"> <input
					type="hidden" value="${MONTH}" name="month">
				<c:forEach var="var2" items="${space2}">
${var2}
</c:forEach>
			</form>
		</table>
	</div>
</body>
</html>