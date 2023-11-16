<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/commonstyle.css">
<link rel="stylesheet" type="text/css" href="../css/center.css">
<title>物件編集完了</title>
</head>
<body>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<div class="menu">
		<%@include file="../tool/admin_menu.jsp" %>
	</div>
	<div class="left-margin"></div>

	<div class="main-contents">
		<h3>物件の編集が完了しました！</h3>
		<p><a href="AdminPropertySearchAll.action"><button>物件一覧に戻る</button></a></p>
	</div>

	<div class="right-margin"></div></body>
</html>