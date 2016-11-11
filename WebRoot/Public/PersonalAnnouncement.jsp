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
	var url;
	//对话框OK选项

	function saveAnnouncement() {
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
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			}
		});
	}

	function editAnnouncement() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑公告');
			$('#fm').form('load', row);
			url = 'updateAnnouncementAction?id=' + row.aid;
		}
	}

	//删除公告
	function destroyAnnouncement() {
		var ids = [];
		var rows = $("#dg").datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].aid);
		}
		var ids = ids.toString();
		if (rows) {
			$.messager.confirm('确定', '确定删除这个公告？', function(r) {
				if (r) {
					$.post('deleteAnnouncementAction', {
						ids : ids
					}, function(result) {
						if (result.success) {
							$("#dg").datagrid('reload');
						} else {
							$.messager.show({
								Title : '错误',
								msg : result.errorMsg
							})
						}
					}, 'json');
				}
			})

		}

	}
	$(function() {
		var row = $('#dg').datagrid('getSelected');
		$('#dg')
				.datagrid(
						{
							title : '查看公告',
							iconCls : 'icon-edit',
							width : 960,
							height : 450,
							singleSelect : true,
							idField : 'usernumber',
							url : 'displayAnnouncementAction',
							columns : [ [
									{
										field : 'title',
										title : '标题',
										width : 50
									},
									{
										field : 'people',
										title : '发布人',
										width : 50
									},
									{
										field : 'pubdate',
										title : '发布时间',
										width : 50
									},
									{
										field : 'action',
										title : '操作',
										width : 70,
										align : 'center',
										formatter : function(value, row, index) {
											var e = '<a href="PAnnouncement.jsp?aid='
													+ row.aid
													+ '" onclick="#" id="edit">详情</a> ';
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
		$.post("usePointAction", {
			"point" : row.bound
		}, function(res) {
			if (res.success) {
				$.messager.show({
					Title : '成功',
					msg : '成功'
				});
			} else {
				$.messager.show({
					Title : '错误',
					msg : '失败'
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
	<table id="dg" class="easyui-datagrid"
		style="width: 100%; height: 100%"
		data-options="rownumbers:true,singleSelect:true,pagination:true"
		url="displayAnnouncementAction" toolbar="#toolbar" rownumbers="true"
		fitColumns="true" singleSelect="false">
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">公告</div>
		<form id="fm" method="post" action="updateAnnouncementAction"
			novalidate>

			<div class="fitem">
				<textarea rows=8 cols=40 class="textarea easyui-validatebox"
					name="announcement" id="announcement"></textarea>
			</div>

		</form>
	</div>

</body>
</html>