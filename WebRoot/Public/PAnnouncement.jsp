<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java"
	import="java.util.*,com.jspsmart.upload.SmartUpload,com.google.gson.JsonObject,dao.AnnouncementDao,utils.JdbcUtils,java.sql.PreparedStatement,java.sql.ResultSet;"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>编辑公告</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<script type="text/javascript" src="index/js/jquery-1.4.2.min.js"></script>
<title></title>
<style type="text/css">
<!--
body {
	background-color: #D7EDFB;
}
-->
</style>
<script type="text/javascript">
	function addComment() {
		var url = "addCommentAction";
		var data = {
			"aid" : $("#aid").val(),
			"username" : $("#username").val(),
			"contentArea" : $("#contentArea").val()
		};
		var success = function(response) {
			$("#content").append(
					"<div style='background-color:#CCCCCC; color:#000000 ;margin:5px'>"
							+ response.username + " 于" + response.commentTime
							+ "发表评论:<br>" + response.content + "</div>");
		}
		$.post(url, data, success, "json");
	}
</script>
</head>
<%
	String announcement = null;
	int aid = Integer.parseInt(request.getParameter("aid"));
	String sql = "select announcement from announcement WHERE aid=" + aid;
	PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(sql);
	ResultSet rs = preparedStatement.executeQuery();
	while (rs.next()) {
		announcement = rs.getString(1);
	}
%>
<body>
	<table width="600" border="0" align="center" cellpadding="0"
		cellspacing="0" bgcolor="#D7EDFB">
		<tr>
			<td>&nbsp;</td>
			<td><img src="ggimages/t1.jpg" width="470" height="52"></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><img src="ggimages/t2.jpg" width="55" height="61"></td>
			<td background="ggimages/t31.jpg"><div align="center">
					<img src="ggimages/t3.jpg" width="470" height="61">
				</div><%=announcement%></td>
			<td><img src="ggimages/t4.jpg" width="59" height="61"></td>
		</tr>
		<tr>
			<td background="ggimages/t5.jpg">&nbsp;</td>
			<td height="400">&nbsp;</td>
			<td background="ggimages/t6.jpg">&nbsp;</td>
		</tr>
		<tr>
			<td><img src="ggimages/t7.jpg" width="55" height="61"></td>
			<td background="ggimages/t9.jpg">&nbsp;</td>
			<td><img src="ggimages/t8.jpg" width="59" height="61"></td>
		</tr>
	</table>
	<div align="center">
		<a href="PersonalAnnouncement.jsp">返回</a>
	</div>
	<br>
	<br> 关于这条公告的评论
	<br>
	<br>
	<div id="content">
		<%
			String sql2 = "select username,content,commentTime from comment where aid=?";
			preparedStatement = JdbcUtils.getConnection().prepareStatement(sql2);
			preparedStatement.setInt(1, aid);
			ResultSet rs2 = preparedStatement.executeQuery();
			while (rs2.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String commentTime = sdf.format(rs2.getTimestamp(3));
		%>
		<div style='background-color: #CCCCCC; color: #000000; margin: 5px'><%=rs2.getString(1)%>
			于<%=commentTime%>发表评论:<br><%=rs2.getString(2)%></div>
		<%
			}
		%>
	</div>
	<br>
	<div>发表评论</div>
	<br>
	<form id="addCommentForm" method="post">
		<table>
			<tr>
				<td colspan="2"><input hidden="hidden" name="aid" id="aid"
					value="<%=request.getParameter("aid")%>"></td>
			</tr>
			<tr>
				<td colspan="2"><input hidden="hidden" name="username"
					id="username" value="<%=session.getAttribute("adminName")%>"></td>
			</tr>
			<tr>
				<td width="100px"><label>评论内容：</label></td>
				<td><textarea name="contentArea" id="contentArea" rows="10"
						cols="50"></textarea></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="button" value="提交"
					onclick="addComment()"></td>
			</tr>
		</table>
	</form>
</body>
</html>
