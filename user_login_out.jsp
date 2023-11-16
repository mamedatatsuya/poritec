<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/commonstyle.css">
<link rel="stylesheet" type="text/css" href="../css/center.css">
<title>ログイン完了</title>
</head>
<body>

	<div class="menu">
		<%@include file="../tool/menu.jsp"%>
	</div>
		<div class=left-margin></div>

	<div class="main-contents">
<div style="text-align: center;">
	<h2>こんにちは、${ user.nickname }さん！</h2>
	<p><a href="top.jsp"><button class="order-button3">トップページ</button></a></p>
</div>
</div>

<div class=right-margin></div>
</body>
</html>
