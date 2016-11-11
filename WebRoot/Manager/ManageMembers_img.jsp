<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.jdbc.PreparedStatement"%>
<%@page import="utils.JdbcUtils"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<link href="resources/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="js/ProvinceAndCity.js"></script>
<link href="resources/style/jquery-ui.css" rel="stylesheet"
	type="text/css" />
</head>

<body class="loginbody">
	<%
		String sql = "select username,img from user";
		PreparedStatement preparedStatement = (PreparedStatement) JdbcUtils.getConnection().prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
	%>
	<div style="float: left; width: 140px; height: 170px; margin: 10px">
		<img src="\Member\upload\<%=rs.getString(2)%>"
			style="width: 130px; height: 150px" /> <span style="align: center"><%=rs.getString(1)%></span>
	</div>
	<%
		}
	%>

</html>



