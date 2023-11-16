<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/commonstyle.css">
<link rel="stylesheet" type="text/css" href="../css/guestusertop.css">
<link rel="stylesheet" type="text/css" href="../css/guestuser_property_search_result.css">
<link rel="stylesheet" type="text/css" href="../css/mypage.css">
<title>物件一覧</title>
</head>
<body>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<div class="menu">
		<%@include file="../tool/menu.jsp"%>
	</div>
	<div class=left-margin></div>

	<div class="main-contents">
<!--物件の検索  -->
		<h1>物件検索</h1>
		<div class="form">
			<form action="GuestUserPropertySearch.action#hit" method="post">

			<p class="headline">間取り</p>

			<div class="chkbox">
				<input type="checkbox" id="chkbox" name="layout" value="ワンルーム">
				<label for="chkbox"><span class="space">ワンルーム</span></label>
				<input type="checkbox" id="chkbox2" name="layout" value="1K">
				<label for="chkbox2"><span class="space">1K</span></label>
				<input type="checkbox" id="chkbox3" name="layout" value="1DK">
				<label for="chkbox3"><span class="space">1DK</span></label>
				<input type="checkbox" id="chkbox4"name="layout" value="1LDK">
				<label for="chkbox4">1LDK</label><br><br>
				<input type="checkbox" id="chkbox5"name="layout" value="2K">
				<label for="chkbox5"><span class="space">2K</span></label>
				<input type="checkbox" id="chkbox6"name="layout" value="2DK">
				<label for="chkbox6"><span class="space">2DK</span></label>
				<input type="checkbox" id="chkbox7"name="layout" value="2LDK">
				<label for="chkbox7">2LDK</label><br><br>
				<input type="checkbox" id="chkbox8"name="layout" value="3K">
				<label for="chkbox8"><span class="space">3K</span></label>
				<input type="checkbox" id="chkbox9"name="layout" value="3DK">
				<label for="chkbox9"><span class="space">3DK</span></label>
				<input type="checkbox" id="chkbox10"name="layout" value="3LDK">
				<label for="chkbox10">3LDK</label><br><br>
				<input type="checkbox" id="chkbox11"name="layout" value="4K">
				<label for="chkbox11"><span class="space">4K</span></label>
				<input type="checkbox" id="chkbox12"name="layout" value="4DK">
				<label for="chkbox12"><span class="space">4DK</span></label>
				<input type="checkbox"id="chkbox13" name="layout" value="4LDK">
				<label for="chkbox13"><span class="space">4LDK</span></label>
				<input type="checkbox" id="chkbox14"name="layout" value="5K以上">
				<label for="chkbox14">5K以上</label><br>
			</div>

			<p class="headline">賃料</p>
				<select name="price_lower">
					<option value="0">下限なし
					<option value="30000">3
					<option value="40000">4
					<option value="50000">5
					<option value="60000">6
					<option value="70000">7
					<option value="80000">8
					<option value="90000">9
					<option value="100000">10
					<option value="150000">15
					<option value="200000">20
					<option value="250000">25
					<option value="300000">30
					<option value="400000">40
					<option value="500000">50
					<option value="600000">60
					<option value="700000">70
					<option value="800000">80
					<option value="900000">90
					<option value="1000000">100
				</select><span class="space2">万円　～ </span>
				<select name="price_upper">
					<option value="0">上限なし
					<option value="30000">3
					<option value="40000">4
					<option value="50000">5
					<option value="60000">6
					<option value="70000">7
					<option value="80000">8
					<option value="90000">9
					<option value="100000">10
					<option value="150000">15
					<option value="200000">20
					<option value="250000">25
					<option value="300000">30
					<option value="400000">40
					<option value="500000">50
					<option value="600000">60
					<option value="700000">70
					<option value="800000">80
					<option value="900000">90
					<option value="1000000">100
				</select>万円<br>

				<p class="headline">ペット可否</p>
				<div class="radiobutton">

					<input type="radio"  id="button-1" name="pet" value="true">
					<label for="button-1">可</label>
					<input type="radio"  id="button-2" name="pet" value="false">
					<label for="button-2">否</label>
					<input type="radio" id="button-3"name="pet" value="どちらでも" checked>
					<label for="button-3">どちらでも</label><br><br>
					<input  id="hit"class="search" type="submit" value="検索">
				</div><br>

   	</form></div>
<%-- <c:forEach var ="p" items ="${list}">
	${property_code}:${property_name}:${layout}:${price}<br>
</c:forEach>
 --%>

<c:choose>
	<c:when test="${list.size()>0}">
		<h2>${list.size()}件の物件があります</h2>
		<hr>
	</c:when>
<c:otherwise>
	<h3>0件の物件があります</h3>
</c:otherwise>
</c:choose>


<%
	int num = 0;
%>
<c:forEach var="item" items="${list}">
	<a href="GuestUserReviewSearch.action?property_code=${item.property_code}&number=<%=num%>"><img class="property-picture" src="../image/${item.image}"></a>
	<ul>
		<li><a class="property_name" href="GuestUserReviewSearch.action?property_code=${item.property_code}&number=<%=num++%>">${item.property_name}</a></li>
		<li>${item.layout}</li>

		<li><fmt:formatNumber value="${item.price/10000}" pattern="###,###.#" />万円</li>
		<li>ペット
			<c:if test="${item.pet ==true }">可</c:if>
			<c:if test="${item.pet ==false }">不可</c:if>
		</li>
	</ul><br>
	<hr>
</c:forEach>
</div>

<div class=right-margin></div>
</body>
</html>