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
<script type="text/javascript">
	//在键盘按下并释放及提交后验证提交表单
	$().ready(
			function() {
				var signupForm = $("#signupForm").validate(
						{
							errorPlacement : function(error, element) {
								$(element).closest("form").find(
										"label[for='" + element.attr("id")
												+ "']").append(error);
							},
							errorElement : "span",

							rules : {
								uname : {
									minlength : 2,
									maxlength : 20
								},
								birthday : {
									dateISO : true
								},
								zjhm : {
									minlength : 8,
									maxlength : 20
								},
								addr : {
									minlength : 2,
									maxlength : 30
								},
								yzbm : {
									minlength : 6,
									maxlength : 20,
									number : true
								},
								Tel : {
									minlength : 6,
									maxlength : 20,
									number : true
								},
								hompage : {
									url : true
								},
								qq : {
									maxlength : 20,
									number : true
								}

							},

							messages : {
								uname : {
									minlength : "(姓名长度不能小于两位)",
									maxlength : "(姓名长度不能超过20位)"
								},
								birthday : {
									dateISO : "请从日期控件中选择"
								},
								zjhm : {
									minlength : "(号码长度不能小于8位)",
									maxlength : "(号码长度不能超过20位)"
								},
								addr : {
									minlength : "(地址长度不能小于2位)",
									maxlength : "(地址长度不能超过30位)"
								},
								yzbm : {
									minlength : "(邮政编码长度不能小于6位)",
									maxlength : "(邮政编码长度不能超过20位)",
									number : "只能输入数字"
								},
								Tel : {
									minlength : "(电话号码长度不能小于6位)",
									maxlength : "(电话号码长度不能超过20位)",
									number : "只能输入数字"
								},
								hompage : {
									url : "请输入有效的网址，以http://或http://开头"
								},
								qq : {
									maxlength : "qq号码长度不能大于20位",
									number : "只能输入数字"
								}
							}
						});
				$("#signupForm").validate({
					submitHandler : function(form) {
						alert("提交事件!");
						form.submit();
					}
				});
			});

	$(function() {
		$("#birthday").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});
	});
</script>

<%
	String username = (String) session.getAttribute("adminName");
	String sql = "select uname from userinfo where username=\'" + username + "\'";
	PreparedStatement preparedStatement = (PreparedStatement) JdbcUtils.getConnection().prepareStatement(sql);
	ResultSet rs = preparedStatement.executeQuery();
	if (!rs.next()) {
%>
<body class="loginbody">
	<div class="dataEye">
		<div class="loginbox registbox">
			<div class="login-content reg-content">
				<div class="loginbox-title">
					<h3>完善个人会员信息</h3>
				</div>
				<form id="signupForm" action="completePersonalSuccessAction"
					method="post">
					<div class="row">
						<%
							String sql3 = "select img from user where username=\'" + username + "\'";
								PreparedStatement preparedStatement3 = (PreparedStatement) JdbcUtils.getConnection()
										.prepareStatement(sql3);
								ResultSet rs3 = preparedStatement3.executeQuery();
								while (rs3.next()) {
						%>
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						<img src="\Member\upload\<%=rs3.getString(1)%>" width="80"
							height="80">

						<%
							}
						%>
					</div>
					<br /> <br /> <br /> <input type="hidden" name="username"
						value="<%=session.getAttribute("adminName")%>" />
					<div class="row">
						<label for="uname">真实姓名</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="uname" id="uname"
							placeholder="真实姓名" required>
					</div>
					<br /> <br />
					<div class="row">
						性别 <select name="sex" id="sex">
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</div>
					<div class="row">
						<label for="birthday">出生日期</label> <input type="text"
							class="input-text-user noPic input-click" name="birthday"
							id="birthday" placeholder="出生日期" required>
					</div>
					<br>
					<div class="row">
						证件类别 <select name="zjlb" id="zjlb">
							<option value="身份证">身份证</option>
							<option value="港澳通行证">港澳通行证</option>
							<option value="学生证">学生证</option>
						</select>
					</div>
					<div class="row">
						<label for="zjhm">证件号码</label> <input type="zjhm" value=""
							class="input-text-password noPic input-click" name="zjhm"
							id="zjhm" placeholder="证件号码" required>
					</div>
					<br>
					<div class="row">
						所在省份<select
							onchange="chinaChange(this,document.getElementById('city'));"
							style="width: 35%; height: 30px; line-height: 30px;" name="szsf">
							<option value="请选择市区">请选择省份</option>
							<option value="北京市">北京市</option>
							<option value="天津市">天津市</option>
							<option value="上海市">上海市</option>
							<option value="重庆市">重庆市</option>
							<option value="河北省">河北省</option>
							<option value="山西省">山西省</option>
							<option value="辽宁省">辽宁省</option>
							<option value="吉林省">吉林省</option>
							<option value="黑龙江省">黑龙江省</option>
							<option value="江苏省">江苏省</option>
							<option value="浙江省">浙江省</option>
							<option value="安徽省">安徽省</option>
							<option value="福建省">福建省</option>
							<option value="江西省">江西省</option>
							<option value="山东省">山东省</option>
							<option value="河南省">河南省</option>
							<option value="湖北省">湖北省</option>
							<option value="湖南省">湖南省</option>
							<option value="广东省">广东省</option>
							<option value="海南省">海南省</option>
							<option value="四川省">四川省</option>
							<option value="贵州省">贵州省</option>
							<option value="云南省">云南省</option>
							<option value="陕西省">陕西省</option>
							<option value="甘肃省">甘肃省</option>
							<option value="青海省">青海省</option>
							<option value="台湾省">台湾省</option>
							<option value="广西壮族自治区">广西壮族自治区</option>
							<option value="内蒙古自治区">内蒙古自治区</option>
							<option value="西藏自治区">西藏自治区</option>
							<option value="宁夏回族自治区">宁夏回族自治区</option>
							<option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
							<option value="香港特别行政区">香港特别行政区</option>
							<option value="澳门特别行政区">澳门特别行政区</option>
						</select>
					</div>
					<div class="row">
						城市<select name="city" id="city"
							style="width: 35%; height: 30px; line-height: 30px;">
							<option value="请选择市区">请选择市区</option>
						</select>
					</div>
					<div class="row">
						<label for="addr">地址</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="addr" id="addr"
							placeholder="地址" required>
					</div>
					<br>
					<div class="row">
						<label for="yzbm">邮政编码</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="yzbm" id="yzbm"
							placeholder="邮政编码" required>
					</div>
					<br>
					<div class="row">
						<label for="Tel">电话</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="Tel" id="Tel"
							placeholder="电话" required>
					</div>
					<br>
					<div class="row">
						<label for="hompage">主页</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="hompage"
							id="hompage" placeholder="主页" required>
					</div>
					<br>
					<div class="row">
						<label for="qq">QQ号</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="qq" id="qq"
							placeholder="QQ号" required>
					</div>
					<br>
					<div class="row btnArea">
						<input type="submit" value="完成" class="login-btn" id="submit">
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
<%
	} else {
		String sql2 = "select uname,sex,birthday,zjlb,zjhm,szsf,city,addr,yzbm,Tel,hompage,qq from userinfo where username=\'"
				+ username + "\'";
		Connection connection2 = JdbcUtils.getConnection();
		PreparedStatement preparedStatement2 = (PreparedStatement) connection2.prepareStatement(sql2);
		ResultSet rs2 = preparedStatement2.executeQuery();
		while (rs2.next()) {
%>
<div class="loginbox">
	<div class="loginbox-title">
		<h3>会员信息</h3>
	</div>
	<div class="login-error"></div>
	<div class="row">
		<label for="username">用户名:</label><span><%=session.getAttribute("adminName")%></span>
	</div>
	<div class="row">
		<label for="uname">真实姓名:</label> <span><%=rs2.getString(1)%></span>
	</div>
	<div class="row">
		<label for="sex">性别:</label> <span><%=rs2.getString(2)%></span>
	</div>
	<div class="row">
		<label for="birthday">出生日期:</label> <span><%=rs2.getString(3)%></span>
	</div>
	<div class="row">
		<label for="zjlb">证件类别:</label> <span><%=rs2.getString(4)%></span>
	</div>
	<div class="row">
		<label for="zjhm">证件号码：</label><span><%=rs2.getString(5)%></span>
	</div>
	<div class="row">
		<label for="szsf">省份:</label> <span><%=rs2.getString(6)%></span>
	</div>
	<div class="row">
		<label for="city">城市:</label><span><%=rs2.getString(7)%></span>
	</div>
	<div class="row">
		<label for="addr">地址:</label> <span><%=rs2.getString(8)%></span>
	</div>
	<div class="row">
		<label for="yzbm">邮政编码:</label> <span><%=rs2.getString(9)%></span>
	</div>
	<div class="row">
		<label for="Tel">电话:</label> <span><%=rs2.getString(10)%></span>
	</div>
	<div class="row">
		<label for="hompage">主页:</label> <span><%=rs2.getString(11)%></span>
	</div>
	<div class="row">
		<label for="qq">qq:</label> <span><%=rs2.getString(12)%></span>
	</div>
</div>
</body>
<%
	}
	}
%>
</html>



