<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/commonstyle.css">
<title>物件編集</title>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="menu">
		<%@include file="../tool/admin_menu.jsp" %>
	</div>
<%
	String num1 = request.getParameter("number");
	int num2 = Integer.parseInt(num1);
%>
<c:set var="num" value="<%=num2 %>"></c:set>
<div class="main-contents">

<p>編集したい項目にチェックを入れると編集ができるようになります</p><br>

<form action="AdminPropertyEditByAdmin.action" method="post" enctype="multipart/form-data">

<table border="1">
	<tr>
		<th>項目</th>
		<th>チェック</th>
		<th>変更内容</th>
		<th>変更後の内容</th>
	</tr>
	<tr>
		<th>物件名</th>
		<td><input type="checkbox" id="checkBox1" onchange="change(1)"><span></span></td>
		<td><input type="text" name="property_name" placeholder="${list[num].property_name }" maxlength="50" required disabled></td>
		<td id="aftername">${list[num].property_name }</td>
	</tr>
	<tr>
		<th>物件説明</th>
		<td><input type="checkbox" id="checkBox2" onchange="change(2)"><span></span></td>
		<td><textarea cols="50" rows="10" placeholder="${list[num].property_content }" maxlength="500" name="property_content" required disabled></textarea></td>
		<td id="aftercontent">${list[num].property_content }</td>
	</tr>
	<tr>
				<th>賃料</th>
		<td><input type="checkbox" id="checkBox3" onchange="change(3)"><span></span></td>
		<td><input type="number" name="price" placeholder="${list[num].price }" min="1000" max="99999999" step="1000" required disabled>円</td>
		<td id="afterprice">${list[num].price }円</td>
	</tr>
	<tr>
		<th>間取り</th>
		<td><input type="checkbox" id="checkBox4" onchange="change(4)"><span></span></td>
		<td>
			<input type="radio" name="layout" value="ワンルーム" disabled>ワンルーム
			<input type="radio" name="layout" value="1K" disabled>1K
			<input type="radio" name="layout" value="1DK" disabled>1Dk
			<input type="radio" name="layout" value="1LDK" disabled>1LDK<br>
			<input type="radio" name="layout" value="2K" disabled>2K
			<input type="radio" name="layout" value="2DK" disabled>2DK
			<input type="radio" name="layout" value="2LDK" disabled>2LDK<br>
			<input type="radio" name="layout" value="3K" disabled>3K
			<input type="radio" name="layout" value="3DK" disabled>3DK
			<input type="radio" name="layout" value="3LDK" disabled>3LDK<br>
			<input type="radio" name="layout" value="4K" disabled>4K
			<input type="radio" name="layout" value="4DK" disabled>4DK
			<input type="radio" name="layout" value="4LDK" disabled>4LDK
			<input type="radio" name="layout" value="5K以上" disabled>5K以上<br>
		</td>

		<td id="afterlayout">${list[num].layout  }</td>
	</tr>
	<tr>
		<th>ペット可否</th>
		<td><input type="checkbox" id="checkBox5" onchange="change(5)"><span></span></td>
		<td>
			<input type="radio" name="pet" value="true" disabled>可
			<input type="radio" name="pet" value="false" disabled>否
		</td>

		<td id="afterpet">
			<c:if test="${list[num].pet ==true }">ペット可</c:if>
			<c:if test="${list[num].pet ==false }">ペット不可</c:if>
		</td>
	</tr>
	<tr>
		<th>画像</th>
		<td><input type="checkbox" id="checkBox6" onchange="change(6)"><span></span></td>
		<td><input type="file" name="image" accept=".jpg, .png, .gif, .jpeg" disabled></td>
		<td><img id="afterimage" src="../image/${list[num].image}" height="100"></td>
	</tr>
</table>

<input type="hidden" name="property_code" value="${list[num].property_code  }">
<input type="submit" value="変更"/>

</form>
</div>
<script type="text/javascript">
const beforename = document.getElementById("aftername").innerText
const beforecontent = document.getElementById("aftercontent").innerText
const beforeprice = document.getElementById("afterprice").innerText
const beforelayout = document.getElementById("afterlayout").innerText
const beforepet = document.getElementById("afterpet").innerText
const beforeimage = document.getElementById("afterimage").src
const before = [beforename, beforecontent, beforeprice, beforelayout, beforepet, beforeimage]
var layout = document.getElementsByName('layout');
var pet = document.getElementsByName('pet');
var layoutnum;
var petnum;

for (var i = 0; i < layout.length; i++){
	if (layout[i].value == beforelayout) {
		layoutnum = i;
		layout[i].checked = true;
	}
}
if (beforepet == "ペット可") {
	petnum = 0;
	pet[0].checked = true;
}else {
	petnum = 1;
	pet[1].checked = true;
}

function change(num) {

    var elements;
    var box;
    var type;
    var after
    if(num == 1){
    	 elements = document.getElementsByName("property_name");
    	 box = document.getElementById("checkBox1");
    	 after = document.getElementById("aftername");
    	 type = "other";
    } else if(num == 2){
    	elements = document.getElementsByName("property_content");
   		 box = document.getElementById("checkBox2");
   		after = document.getElementById("aftercontent");
   	 	type = "other";
    } else if(num == 3){
    	elements = document.getElementsByName("price");
   	 	box = document.getElementById("checkBox3");
   	 after = document.getElementById("afterprice");
   	 	type = "other";
    } else if(num == 4){
    	elements = document.getElementsByName("layout");
   	 	box = document.getElementById("checkBox4");
   	 	after = document.getElementById("afterlayout");
   	 	type = "radio";
    } else if(num == 5){
    	elements = document.getElementsByName("pet");
   	 	box = document.getElementById("checkBox5");
   	 	after = document.getElementById("afterpet");
   	 	type = "radio";
    } else if(num == 6){
    	elements = document.getElementsByName("image");
   	 	box = document.getElementById("checkBox6");
   	 	after = document.getElementById("afterimage");
   	 	type = "other";
    }
    if(box.checked) {
        elements.forEach(e => {
            e.disabled = false;
        });
    }else {
    	elements.forEach(e => {
            e.disabled = true;
            if(num == 6){
            	after.src = before[num - 1];
            }else{
            	after.innerText = before[num - 1];
            }
            if(type == "other"){
            	e.value = "";
            }else if(num == 4){
            	layout[layoutnum].checked = true;
            }else if(num == 5){
            	pet[petnum].checked = true;
            }
        });
    }
}
document.getElementsByName("property_name").forEach(
        r => r.addEventListener("change" ,
                 e => document.getElementById("aftername").innerText = e.target.value)
            )
document.getElementsByName("property_content").forEach(
        r => r.addEventListener("change" ,
                 e => document.getElementById("aftercontent").innerText = e.target.value)
            )
document.getElementsByName("property_content").forEach(
        r => r.addEventListener("input" ,
                 e => {
                	 if (!e.target.value.match(/\S/)) {
                		 e.target.value = "";
					}
                 })
            )
document.getElementsByName("price").forEach(
        r => r.addEventListener("change" ,
                 e => document.getElementById("afterprice").innerText = e.target.value)
            )
document.getElementsByName("layout").forEach(
        r => r.addEventListener("change" ,
                 e => document.getElementById("afterlayout").innerText = e.target.value)
            )
document.getElementsByName("pet").forEach(
        r => r.addEventListener("change" ,
                 e => {
                	 if(e.target.value == "true"){
                		 document.getElementById("afterpet").innerText = "ペット可";
                	 } else {
                		 document.getElementById("afterpet").innerText = "ペット不可";
                	 }
                 } )
            )
document.getElementsByName("image").forEach(
        r => r.addEventListener("change" ,
                 e => {
                	 var file = e.target.files[0];
                	 var blobUrl = window.URL.createObjectURL(file);
                	 document.getElementById("afterimage").src = blobUrl;
                 })
            )
</script>


</body>
</html>