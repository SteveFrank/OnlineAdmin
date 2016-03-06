<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
	});  
</script>
<script type="text/javascript">
function verifyCode_change(){
	var image = document.getElementById("vCode");
	image.src = "<c:url value='/verifyCode'/>?xxx=" + new Date().getTime();
}
function submit() {
	document.forms["form"].submit();
}
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo">
    </span> 
       
    <div class="loginbox loginbox1">
    <form name="form" action="<c:url value='/login'/>" method="post">
    <ul>
   	
    <li><input name="email" type="text" class="loginuser" value="1044098444@qq.com" id="email" onclick="JavaScript:this.value=''"/></li>
    <li><input name="password" type="text" class="loginpwd" value="密码" id="password" onclick="JavaScript:this.value=''"/></li>
    <li class="yzm">
    <span><input name="verifyCode" id="verifyCode" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span>
    <cite>
    	<!-- X3D5S -->
    	<img id="vCode" alt="点击更换" title="点击更换" src="<c:url value='/verifyCode' />" name="vCode" onclick="verifyCode_change()" class="m">
    </cite> 
    </li>
    <li>
    <input name="submit" type="submit" class="loginbtn" value="登录" onclick="submit()" />
    <label>
    	<input name="" type="checkbox" value="" checked="checked" />记住密码
    </label>
    <label><a href="#">忘记密码？</a></label>
    </li>
    </ul>
    <font size="40px" color="red">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    ${msg }
    </font>
    </form>
    </div>
    
    </div>
    
</body>

</html>
