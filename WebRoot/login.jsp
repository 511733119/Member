<%@ page language="java" import="java.util.*,java.net.URLDecoder" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%
	 String username = "";
	 String password = "";
	 Cookie[] c = request.getCookies();
	 if(c != null && c.length > 0)
	 {
	   for(Cookie c1 : c)
	   {
	     if(c1.getName().equals("username"))
	     {
	       username = URLDecoder.decode(c1.getValue(),"utf-8");
	     }
	     if(c1.getName().equals("password"))
	     {
	       password = URLDecoder.decode(c1.getValue(),"utf-8");
	     }
	   }
	 }
  %>
  <%
	//获取请求上下文
	String context = request.getContextPath();
   %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>登录</title>
		<!-- Custom Theme files -->
		<link href="<%=context%>/css/style1.css" rel="stylesheet" type="text/css" media="all"/>
		<!-- Custom Theme files -->
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		<meta name="keywords" content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
		<!--Google Fonts-->
		<link href='http://fonts.useso.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
		<!--Google Fonts-->
	</head>
	
	<script type="text/javascript">
	   //点击验证码图片会重新加载验证码,Math.random()的作用的每次都传递不同的参数，
	   //从而每次都生成一个新的验证码对象
	     function loadImage(){
			  document.getElementById("code").src = "image.jsp?"+Math.random(); 
	     }
	</script>

<body>
	<div class="login">
		 <div class="login-main">
	 		<div class="login-top">
	 			<img src="images/head-img.png" alt=""/>
	 			<h1>登录</h1>
	 			<form action="<%=context%>/loginCheckAction" id="form" method="post">
	 			<input type="text" name="username" value="<%=username%>" placeholder="用户名" required>
	 			<input type="password" name="password" value="<%=password%>" placeholder="密码" required>
	 			<input type="text" id="checkcode" name="checkcode" width="200" placeholder="输入验证码" required>
	 			<img src="image.jsp"  height="30px" id="code"  onClick="javascript:loadImage();"/>
	 			<div class="login-bottom">
	 				<div class="login-check">
		 				<input type="checkbox" checked="checked" name="remember"/><i> </i> 记 住 我
		 		    </div>
		 			<div class="clear"> </div>
	 			</div>
	 			  <c:if test="${!empty errorMessage }">
			      	<font color="red">${errorMessage }</font>
			      </c:if>
			      <br><br>
			     
	 			<input type="submit" value="登录" /><br><br>
	 			没有账号？<a href="register.jsp">注册</a>
	 			</form>
	 		</div>
	 	</div>
  </div>
</body>
</html>