<%@page import="java.sql.ResultSet"%>
<%@page import="utils.JdbcUtils"%>
<%@page import="com.mysql.jdbc.PreparedStatement"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>兑换积分</title>
<script src="eui/jquery.min.js"></script>
<script src="eui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="eui/themes/icon.css">
<link rel="stylesheet" href="eui/themes/default/easyui.css">
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
<script>
	var url;
	//点击打开兑换积分菜单
	$(function() {
		$("#dlg").dialog('open').dialog('setTitle', '兑换积分');
		$("#fm").form('clear');
		url = "exchangeAction"
	});

	function confirmSave() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.errorMsg) {
					$.messager.show({
						title : 'Error',
						msg : result.errorMsg
					});
				} else {
					$('#dlg').dialog('close');
					$.messager.show({
						msg : '成功兑换'
					})
				}
			}
		});
	}

	function cancelrow(target) {
		$('#dg').datagrid('cancelEdit', getRowIndex(target));
	}

	//确认弹出框;  
	function confirmInfo() {
		jQuery.messager.confirm('提示:', '确定兑换吗?', function(event) {
			if (event) {
				confirmSave();
			} else {
			}
		});
	}
</script>
</head>

<body>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle"></div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">

				<span>卡号:<input type="text" name="CardNumber"></span> <br>
				<br>
				<br> <span>密码:<input type="password" name="CardPassword"></span>
				<br>
				<br> <br> <span>需要用多少点数兑换积分? <select name="Point"
					id="Point" style="width: 60px;">
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="15">15</option>
						<option value="20">20</option>
						<option value="25">25</option>
						<option value="30">30</option>
						<option value="50">50</option>
				</select>
				</span> <br> <span><font color="red">(1个点数可兑换10个积分)</font></span> <br>
			</div>

		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="confirmInfo()">保存</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>