<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/commonstyle.css">
<link rel="stylesheet" type="text/css" href="../css/menu.css">
<link rel="stylesheet" type="text/css" href="../css/admin_menu.css">
<title>物件登録</title>
</head>
<body>
	<div class="menu">
		<%@include file="../tool/admin_menu.jsp" %>
	</div>

	<div class="left-margin"></div>

	<div class="main-contents">

		<h2>物件登録</h2>

		<p>登録する物件内容を入力してください</p>

		<form action="AdminPropertyInsert.action" method="post" enctype="multipart/form-data">

			<p>物件名
			<input type="text" name="property_name" placeholder="2～50文字まで" pattern=".*\S+.*" minlength="2" maxlength="50" required></p><br>

			<p>物件説明<br>
			<textarea cols="50" rows="10" placeholder="500文字まで" maxlength="500" name="property_content" required></textarea></p><br>

					<p>賃料
			<input type="number" name="price"  min="1000" max="99999999" step="1000" required>円</p><br>

			<p>間取り<br>
			<input type="radio" name="layout" value="ワンルーム" checked="checked">ワンルーム
			<input type="radio" name="layout" value="1K">1K
			<input type="radio" name="layout" value="1DK">1Dk
			<input type="radio" name="layout" value="1LDK">1LDK<br>
			<input type="radio" name="layout" value="2K">2K
			<input type="radio" name="layout" value="2DK">2DK
			<input type="radio" name="layout" value="2LDK">2LDK<br>
			<input type="radio" name="layout" value="3K">3K
			<input type="radio" name="layout" value="3DK">3DK
			<input type="radio" name="layout" value="3LDK">3LDK<br>
			<input type="radio" name="layout" value="4K">4K
			<input type="radio" name="layout" value="4DK">4DK
			<input type="radio" name="layout" value="4LDK">4LDK
			<input type="radio" name="layout" value="5K以上">5K以上</p><br>

			<p>ペットの可否<br>
			<input type="radio" name="pet" value="true" checked="checked">可
			<input type="radio" name="pet" value="false">否</p><br>

			<br>

			画像の選択（アップロード）
			<input type="file" name="image" accept=".jpg, .png, .gif, .jpeg" >
			<img id="addimage"  src="../image/sample.jpg"><br>

			<input class="order-button" type="submit" value="登録">
			<script type="text/javascript">
			document.getElementsByName("image").forEach(
			        r => r.addEventListener("change" ,
			                 e => {
			                	 var file = e.target.files[0];
			                	 var blobUrl = window.URL.createObjectURL(file);
			                	 document.getElementById("addimage").src = blobUrl;
			                 })
			            )
			document.getElementsByName("property_content").forEach(
			        r => r.addEventListener("input" ,
			                 e => {
			                	 if (!e.target.value.match(/\S/)) {
			                		 e.target.value = "";
								}
			                 })
			            )
			</script>
		</form>
	</div>

	<div class="right-margin"></div>

</body>
</html>