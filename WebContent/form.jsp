<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">提交的审批信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle">
    <span>
    	审批信息 || 状态：
    	<c:choose>
    		<c:when test="${seller.accpetActive eq '0' }">
    			<font color="blue">未审批</font>
    		</c:when>
    		<c:when test="${seller.accpetActive eq '1' }">
    			<font color="green">审批通过</font>
    		</c:when>
    		<c:otherwise>
    			<font color="red">审批未过</font>
    		</c:otherwise>
    	</c:choose>
    </span></div>
    
    <ul class="forminfo">
    <li><label>商家编号：</label><input disabled="disabled" type="text" class="dfinput" value="${seller.sid }" /><i>网站商铺唯一编号</i></li>
    <li><label>商家名称：</label><input disabled="disabled" type="text" class="dfinput" value="${seller.salename }" /><i>商铺名称用户可见</i></li>
    <li><label>联系邮箱：</label><input disabled="disabled" type="text" class="dfinput" value="${seller.email }" /></li>
    <li><label>联系电话：</label><input disabled="disabled" type="text" class="dfinput" value="${seller.phone }" /></li>
    <li><label>成立时间：</label><input disabled="disabled" type="text" class="dfinput" value="${seller.date }" /></li>
    <li><label>身份证明：</label><img width="250px" height="200px" src="<c:url value='${seller.verfiyCard }' />" /></li>
    <li><label>营业执照：</label><img width="250px" height="200px" src="<c:url value='${seller.salefile }' />" /></li>
    <li><label>卫生许可：</label><img width="250px" height="200px" src="<c:url value='${seller.safefile }' />" /></li>
    </ul>
    
    </div>
	
	<div class="tools">
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span><a href="<c:url value='/passSeller?sid=${seller.sid }'/>">审核通过</a></li>
        <li class="click"><span><img src="images/t02.png" /></span><a href="<c:url value='/cancleSeller?sid=${seller.sid }'/>">审核驳回</a></li>
        </ul>
    </div>
    
</body>

</html>
