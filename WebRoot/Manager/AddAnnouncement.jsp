<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.jdbc.PreparedStatement"%>
<%@page import="utils.JdbcUtils"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>添加公告</title>
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
<script>

$(function() {
	$("#pubdate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd'
	});
});
</script>
<body class="loginbody">
	<div class="dataEye">
		<div class="loginbox registbox">
			<div class="login-content reg-content">
				<div class="loginbox-title">
					<h3>添加公告</h3>
				</div>
				<form id="signupForm" action="addAnnouncementAction"
					method="post">
					<div class="login-error"></div>
					<div class="row">
						<label for="title">标题</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="title" id="title"
							placeholder="标题" required>
					</div>
					<br/>
					<div class="row">
						<label for="pubdate">发布日期</label> <input type="text"
							class="input-text-user noPic input-click" name="pubdate"
							id="pubdate" placeholder="发布日期" required>
					</div>
					<br>
					<div class="row">
						<label for="announcement">公告内容</label><br/>
						<textarea id="" rows=10 cols=45 class="textarea easyui-validatebox" name="announcement" id="announcement"></textarea>
					</div>
					<br/><br/><br/><br/><br/><br/><br/><br/>
					<div class="row btnArea">
						<input type="submit" value="添加" class="login-btn" id="submit">
					</div>
				</form>
			</div>
			<div class="go-regist">
				<a href="ManageAnnouncement.jsp" class="link">返回</a>
			</div>
		</div>
	</div>

</body>

</html>



