<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/commonstyle.css">
<link rel="stylesheet" type="text/css" href="../css/center.css">
<title>物件登録完了</title>
</head>
<body>
	<div class="menu">
		<%@include file="../tool/admin_menu.jsp" %>
	</div>
	<div class="main-contents">

		<h3>物件の登録が完了しました！</h3>

		<form action="admin_property_insert_in.jsp">
			<input class="orderbutton" type="submit" value="続けて登録">
		</form>

		<p><a href="admin_top.jsp"><button class="orderbutton">トップページ</button></a></p><br>
	</div>
</body>
</html>