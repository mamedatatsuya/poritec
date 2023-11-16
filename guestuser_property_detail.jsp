<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/commonstyle.css">
<link rel="stylesheet" type="text/css" href="../css/property_detail.css">
<title>物件詳細</title>
</head>
<body>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<div class="menu">
			<%@include file="../tool/menu.jsp"%>
	</div>

	<div class=left-margin></div>

	<div class="main-contents">
		<h2 class="nomargin">物件詳細</h2>
		<%-- <p class="nomargin">物件コード：${property.property_code }</p> --%>
		<img class="property-picture" src="../image/${property.image}">
		<table class="property-table">
			<tr><th>物件名</th><td>${property.property_name }</td></tr>
			<tr><th>間取り</th><td>${property.layout }</td></tr>
			<tr><th>賃料</th><td><fmt:formatNumber value="${property.price/10000}" pattern="###,###.#" />万円</td></tr>
			<tr><th>ペット可否</th>
				<td><c:if test="${property.pet ==true }">可</c:if>
					<c:if test="${property.pet ==false }">不可</c:if>
				</td>
			</tr>
			<tr><th>物件説明</th><td>${property.property_content }</td></tr>
		</table>
		<br>
		<br>
		<hr class="line">

		<%-- ${property.image } --%>

		<p><a href="guestuser_property_search_result.jsp#hit">
		<button class="order-button3">物件一覧に戻る</button></a></p>

		<c:if test="${!(empty user)}">
			<form action="user_review_insert_confirm.jsp">
				<h2>クチコミ投稿</h2>
				<textarea  cols="50" rows="10" pattern=".*\S+.*" maxlength="500" placeholder="500文字まで" name="review"  maxlength="50" required ></textarea><br>
				<input type="hidden" name="property_code"value="${property.property_code }">
				<input type="submit" value="投稿">
			</form><br>
		</c:if>

		<h2>この物件に関するクチコミ</h2>
		<c:choose>
			<c:when test="${reviewList.size()>0}">
				<c:forEach var="r" items="${reviewList}">
					<hr>
					<tr>
						<%-- <td>${r.review_code}</td> --%>
						<c:forEach var="u" items="${users}">
							<c:if test="${ r.account_code == u.account_code}">
								<td>${u.nickname}　</td>
							</c:if>
						</c:forEach>
						<td>${r.review_content}</td>
					</tr>
					<br><br><br>
				</c:forEach>
				<br>
			</c:when>
			<c:otherwise>
				<p>この物件に寄せられたクチコミはありません</p>
				<br><br><br>
			</c:otherwise>
		</c:choose>

	</div>

	<div class=right-margin></div>

</body>
</html>