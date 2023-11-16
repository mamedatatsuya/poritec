<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/commonstyle.css">
<link rel="stylesheet" type="text/css" href="../css/center.css">
<title>ユーザーログイン</title>
</head>
<body>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="menu">
		<%@include file="../tool/menu.jsp"%>
	</div>
	<div class=left-margin></div>

	<div class="main-contents">

	<h2>ログインIDとパスワードを入力してください</h2>

	<form action="UserLogin.action" method="post">
		<p class="nomargin headline">ログインID</p>
		<input type="text" name="login_id" value="${user_id}" minlength="6" maxlength="16" pattern="^[a-zA-Z0-9]+$" required><br>
		<c:if test="${!(empty idError)}"><p class="error-message">IDが違います</p></c:if><br>

		<p class="nomargin headline">パスワード</p>
		<input type="password" name="password" minlength="6" maxlength="16" pattern="^[a-zA-Z0-9]+$" required>

		<c:if test="${!(empty passError)}"><p class="error-message">パスワードが違います</p></c:if>
		<br>
		<p><input class="order-button2" type="submit" value="ログイン"></p>
	</form>


		会員登録がまだの方はこちらから
		<!-- <p class="nomargin"><a href="../guest_user/guest_insert_in.jsp">新規会員登録</a></p> -->
		<form action="../guest_user/guest_insert_in.jsp">
    		<button class="order-button2">会員登録</button>
		</form>
</div>

<div class=right-margin></div>
</body>
</html>
