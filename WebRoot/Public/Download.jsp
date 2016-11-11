<%@ page language="java" import="java.util.*,com.jspsmart.upload.SmartUpload,com.google.gson.JsonObject,dao.MemberDao;" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'download.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <% 
		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

	    SmartUpload su = new SmartUpload();
		su.initialize(pageContext);
		su.setContentDisposition(null);
		su.downloadFile("D:/eclipse for javaEE/Eclipse IDE for Java EE Developers_win64/eclipse/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Member/product/1.txt");
		String username = (String) session.getAttribute("adminName");
		// 获取商品积分
		Integer point = Integer.valueOf(request.getParameter("point"));
    %>
  </body>
</html>
