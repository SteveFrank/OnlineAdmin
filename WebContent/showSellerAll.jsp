<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>
</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">商家列表</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span>添加</li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        </ul>
        
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
	    
    <table class="imgtable">
    
    <thead>
    <tr>
    <th width="100px;">身份证</th>
    <th>商户名称</th>
    <th>商户邮箱</th>
    <th>成立时间</th>
    <th>是否审核</th>
    <th>开始审核</th>
    </tr>
    </thead>
    
    <tbody>
    <c:forEach items="${pb.beanList}" var="seller">
    	<tr>
    		<td class="imgtd"><img width="120px" height="100px" src="${seller.verfiyCard }" /></td>
    		<td><a href="#">${seller.salename }</a><p>商铺规模：${seller.states }</p></td>
    		<td>${seller.email }<p>商户电话:${seller.phone }</p></td>
    		<td>${seller.date }</td>
    		<td>
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
			</td>
    		<td><a href="<c:url value='/preAccpetSeller?sid=${seller.sid }' />">审核商铺</a></td>
    	</tr>
    </c:forEach>
    
    </tbody>
    
    </table>
    
    <div class="pagin">
    	<div class="message">共<i class="blue">${pb.rowCount}</i>条记录，当前显示第&nbsp;
    	<i class="blue">${pb.pageCurrent}&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        
                    <tr class="tr_pagenumber">
                <td colspan="100">
                	&nbsp;&nbsp;
                	<li class="paginItem"><a href="${pb.url}&PageCurrent=1">首页</a></li>
                	<c:choose>
						<c:when test="${pb.pageCurrent < pb.pageCount}">
						<li class="paginItem"><a href="${pb.url}&PageCurrent=${pb.pageCurrent+1}">下一页</a></li>
					</c:when>
					
					<c:when test="${pb.pageCurrent >= pb.pageCount}">
						<li class="paginItem"><a href="javascript:;">没有</a></li>
					</c:when>
					</c:choose>
					
					<%--计算标签的的列表的起始 --%>
					<c:choose>
						<%--如果总页数不足10，那么显示所有页面 --%>
						<c:when test="${pb.pageCount<=10}">
							<c:set var="begin" value="1"></c:set>
							<c:set var="end" value="${pb.pageCount}"></c:set>
						</c:when>
						<c:otherwise>
							<%--当总页数>10时候,通过公式计算出begin和end --%>
							<c:set var="begin" value="${pb.pageCurrent-5}"></c:set>
							<c:set var="end" value="${pb.pageCurrent+4}"></c:set>
							<%--头溢出 --%>
							<c:if test="${begin<1}">
								<c:set var="begin" value="1"></c:set>
								<c:set var="end" value="10"></c:set>
							</c:if>
							<%--尾溢出 --%>
							<c:if test="${end>pb.pageCount}">
								<c:set var="begin" value="${pb.pageCount-10+1}"></c:set>
								<c:set var="end" value="${pb.pageCount}"></c:set>
							</c:if>
						</c:otherwise>
					</c:choose> 
					<%--循环遍历页码列表 --%> 
					<c:forEach var="i" begin="${begin}" end="${end}">
					<c:choose>
						<c:when test="${i eq pb.pageCurrent}">
							<li class="paginItem current"><a href="javascript:;">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="paginItem"><a href="${pb.url}&PageCurrent=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
					</c:forEach> 
                    <c:choose>
						<c:when test="${pb.pageCurrent > 1}">
							<li class="paginItem"><a href="${pb.url}&PageCurrent=${pb.pageCurrent-1}">上一页</a></li>
						</c:when>
						<c:when test="${pb.pageCurrent <= 1}">
							<li class="paginItem current"><a href="javascript:;">没有</a></li>
						</c:when>
					</c:choose>
					<li class="paginItem"><a href="${pb.url}&PageCurrent=${pb.pageCount}">尾页</a></li>
					<li class="paginItem"><a href="${pb.url}&PageCurrent=${pb.pageCount}"><span class="pagenxt"></span></a></li>
                </td>
            </tr>
        </ul>
    </div>
	    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
	</div>
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    </div>
<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
</body>
</html>