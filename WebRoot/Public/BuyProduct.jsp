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
<meta charset="UTF-8">
<title>主页</title>
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
	$(function() {
		$('#dg').datagrid(
			{
				title : '下载文档',
				iconCls : 'icon-edit',
				width : 960,
				height : 450,
				singleSelect : true,
				idField : 'usernumber',
				url : 'displayBoundAction',
				columns : [ [
						{
							field : 'ProductID',
							title : '产品编号',
							width : 50
						},
						{
							field : 'Version',
							title : '产品版本',
							width : 50
						},
						{
							field : 'PType',
							title : '产品类型',
							width : 50
						},
						{
							field : 'Content',
							title : '产品描述',
							width : 100
						},
						{
							field : 'bound',
							title : '积分',
							width : 80,
							align : 'left'
						},
						{
							field : 'action',
							title : '操作',
							width : 70,
							align : 'center',
							formatter : function(value, row, index) {
								var e = '<a href="Download.jsp" id="down" onclick="return saverow(this)">下载</a> ';
								var d = '';
								return e + d;
							}
						} ] ],
				onBeforeEdit : function(index, row) {
					row.editing = true;
					updateActions(index);
				},
				onAfterEdit : function(index, row) {
					row.editing = false;
					updateActions(index);
				},
				onCancelEdit : function(index, row) {
					row.editing = false;
					updateActions(index);
				}
			});
	});

	function updateActions(index) {
		$('#dg').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}

	function getRowIndex(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}

	function editrow(target) {
		$('#dg').datagrid('beginEdit', getRowIndex(target));
	}

	function saverow(target) {
		$('#dg').datagrid('endEdit', getRowIndex(target));
		var row = $('#dg').datagrid('getSelected');
		var jifen = $('#currentjifen').val();
		if (row.bound > jifen) {
			alert('当前积分不足以下载此文档！');
			return false;
		}
		$.post("downloadAction", {
			"point" : row.bound,
			"ProductID" : row.ProductID
		}, function(res) {
			if (!res.success) {
				$.messager.show({
					Title : '错误',
					msg : '积分不足，请充值'
				})
			}
		}, 'json');
	}

	function cancelrow(target) {
		$('#dg').datagrid('cancelEdit', getRowIndex(target));
	}
</script>
</head>
<body>
	<%
		String username = (String) session.getAttribute("adminName");
		String sql = "select point from user where username=\'" + username + "\'";
		PreparedStatement preparedStatement = (PreparedStatement) JdbcUtils.getConnection().prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
	%>
	<font color="red">当前积分:</font>
	<input id="currentjifen" value="<%=rs.getInt(1)%>" style="width: 20px"
		disabled="true">
	<%
		}
	%>
	<table id="dg" class="easyui-datagrid"
		style="width: 100%; height: 100%"
		data-options="rownumbers:true,singleSelect:true,pagination:true"
		url="displayBoundAction" toolbar="#toolbar" rownumbers="true"
		fitColumns="true" singleSelect="true">
	</table>

</body>
</html>