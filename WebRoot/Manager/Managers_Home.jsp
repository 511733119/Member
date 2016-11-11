<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>会员信息管理系统后台</title>
<link href="index/css/default.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="index/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="index/js/themes/icon.css" />
<script type="text/javascript" src="index/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="index/js/jquery.easyui.pack.js"></script>

<script type="text/javascript" src='index/js/outlook2.js'>
	
</script>

<script type="text/javascript">
	var _menus = {
		"menus" : [ {
			"menuid" : "1",
			"icon" : "icon-sys",
			"menuname" : "会员管理",
			"menus" : [ {
				"menuname" : "个人会员管理",
				"icon" : "icon-sys",
				"url" : "PersonalMember.html"
			}, {
				"menuname" : "企业会员管理",
				"icon" : "icon-remove",
				"url" : "CompanyMember.html"
			}, {
				"menuname" : "会员组管理",
				"icon" : "icon-users",
				"url" : "ManageMember.html"
			} ]
		}, {
			"menuid" : "2",
			"icon" : "icon-sys",
			"menuname" : "商品管理单",
			"menus" : [ {
				"menuname" : "添加会员商品",
				"icon" : "icon-sys",
				"url" : "Product.html"
			}, {
				"menuname" : "交易明细",
				"icon" : "icon-remove",
				"url" : "ManagerShowBuyProduct.html"
			} ]
		}, {
			"menuid" : "3",
			"icon" : "icon-sys",
			"menuname" : "积分点卡",
			"menus" : [ {
				"menuname" : "点卡管理",
				"icon" : "icon-sys",
				"url" : "Card.html"
			}, {
				"menuname" : "商品积分",
				"icon" : "icon-remove",
				"url" : "Bound.html"
			}, {
				"menuname" : "会员积分",
				"icon" : "icon-remove",
				"url" : "Point.html"
			} ]
		}, {
			"menuid" : "4",
			"icon" : "icon-sys",
			"menuname" : "其他项",
			"menus" : [ {
				"menuname" : "公告管理",
				"icon" : "icon-sys",
				"url" : "ManageAnnouncement.jsp"
			}, {
				"menuname" : "相册管理",
				"icon" : "icon-remove",
				"url" : "ManageMembers_img.jsp"
			} ]
		} ]
	};
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	//关闭登录窗口
	function closePwd() {
		$('#w').window('close');
	}

	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}
		//修改密码,使用ajax表单post到servlet
		$.post('changePwdAction?newpass=' + $newpass.val(), function(msg) {
			msgShow('系统提示', '密码修改成功');
			$newpass.val('');
			$rePass.val('');
			close();
		})

	}

	$(function() {

		openPwd();
		//
		$('#editpass').click(function() {
			$('#w').window('open');
		});

		$('#btnEp').click(function() {
			serverLogin();
		})

		$('#btnCancel').click(function() {
			closePwd();
		})

		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

				if (r) {
					location.href = 'logout.jsp';
				}
			});

		})

	});
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="index/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px; background: url(index/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<span style="float: right; padding-right: 20px;" class="head">欢迎
			<font color="red"><%=session.getAttribute("adminName")%></font> <a
			href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 16px;"><img
			src="index/images/blocks.gif" width="20" height="20"
			align="absmiddle" /> 会员管理系统后台</span>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;"></div>
	<div region="west" split="true" title="导航菜单" style="width: 180px;"
		id="west">
		<div class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->

		</div>

	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">

				<h1>欢迎使用会员管理系统后台</h1>
				<p>点击左侧按钮</p>

			</div>
		</div>
	</div>


	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input name="npwd" id="txtNewPass" type="password"
							class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a id="btnCancel"
					class="easyui-linkbutton" icon="icon-cancel"
					href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>