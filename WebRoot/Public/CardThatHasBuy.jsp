<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<title>已购买的点卡</title>
<script src="eui/jquery.min.js"></script>
<script src="eui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="eui/themes/icon.css">
<link rel="stylesheet" href="eui/themes/default/easyui.css">
</head>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}
</style>
<body>
	<table id="dg" title="已购买的点卡" class="easyui-datagrid"
		style="width: 100%; height: 100%"
		data-options="rownumbers:true,singleSelect:true,pagination:true"
		url="displayCardThatHasBuyAction" rownumbers="true" fitColumns="true"
		singleSelect="false">
		<thead>
			<tr>
				<th field="CardNumber" width="22">账号</th>
				<th field="CardPassword" width="22">密码</th>
				<th field="Point" width="22">剩余点数</th>
				<th field="TimeOutDate" width="50">过期时间</th>
				<th field="AddTime" width="22">购买时间</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"">

	</div>
</body>
</html>