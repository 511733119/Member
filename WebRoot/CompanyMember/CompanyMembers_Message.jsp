<%@page import="java.sql.Connection"%>
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
<html>
<head>
<title>完善会员信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<link href="resources/style/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.12.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="js/select.js"></script>
<script type="text/javascript" src="js/ProvinceAndCity.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$("#birthday").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});
	});

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
								C_Name : {
									minlength : 4,
									maxlength : 30
								},
								C_PostCode : {
									minlength : 4,
									maxlength : 20,
									number : true
								},
								C_Address : {
									maxlength : 50
								},
								C_ConactName : {
									minlength : 2,
									maxlength : 20
								},
								C_WebSite : {
									url : true
								},
								C_Tel : {
									minlength : 5,
									maxlength : 10,
									number : true
								},
								C_BankName : {
									minlength : 2,
									maxlength : 20
								},
								C_Capital : {
									range : [ 0, 999999999999999999999999999 ],
									number : true
								}
							},

							messages : {
								C_Name : {
									minlength : "(公司名称长度至少为4位)",
									maxlength : "(公司名称长度最多为30位)"
								},
								C_PostCode : {
									minlength : "(邮政编码长度至少为4位)",
									maxlength : "(邮政编码长度至少为20位)",
									number : "(请输入数字)"
								},
								C_Address : {
									maxlength : "(地址长度不能超过50位)"
								},
								C_ConactName : {
									minlength : "(姓名长度至少为2位)",
									maxlength : "(姓名长度不能超过20位)"
								},
								C_WebSite : {
									url : "(请输入一个有效的网址,以http/https://开头)"
								},
								C_Tel : {
									minlength : "(电话长度至少为5位)",
									maxlength : "(电话长度最多为10位)",
									number : "(只能输入数字)"
								},
								C_BankName : {
									minlength : "(姓名长度至少为2位)",
									maxlength : "(姓名长度不能超过20位)"
								},
								C_Capital : {
									range : "请输入正确的大于0的数字",
									number : "请输入正确的数字"
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
</script>

<%
	String username = (String) session.getAttribute("adminName");
	String sql = "select C_Name from companyuserinfo where username=\'" + username + "\'";
	PreparedStatement preparedStatement = (PreparedStatement) JdbcUtils.getConnection().prepareStatement(sql);
	ResultSet rs = preparedStatement.executeQuery();
	if (!rs.next()) {
%>

<body class="loginbody">
	<div class="dataEye">
		<div class="loginbox registbox">
			<div class="login-content reg-content">
				<div class="loginbox-title">
					<h3>完善会员信息</h3>
				</div>
				<form id="signupForm" action="completeCompanySuccessAction"
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
					<br /> <br /> <br />
					<div class="row">
						<label for="username">用户名</label> <span id="username"
							name="username"><%=session.getAttribute("adminName")%></span>
					</div>
					<div class="row">
						<label for="C_Name">公司名称</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="C_Name"
							id="C_Name" required>
					</div>
					<br>
					<div class="row">
						<label for="C_PostCode">邮政编码</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="C_PostCode"
							id="C_PostCode" required>
					</div>
					<br>
					<div class="row">
						请选择省份：<select name="C_Province" id="C_Province"
							onchange="chinaChange(this,document.getElementById('C_City'));"
							style="width: 30%; height: 30px; line-height: 30px;" required>
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
						请选择市区：<select name="C_City" id="C_City"
							style="width: 30%; height: 30px; line-height: 30px;">
							<option value="请选择市区">请选择市区</option>
						</select>
					</div>
					<div class="row">
						<label for="C_Address">公司地址</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="C_Address"
							id="C_Address" required>
					</div>
					<br>
					<div class="row">
						<label for="C_ConactName">联系人姓名</label> <input type="text"
							value="" class="input-text-user noPic input-click"
							name="C_ConactName" id="C_ConactName" required>
					</div>
					<br>
					<div class="row">
						联系人职业：<select name="C_Vocation" id="C_Vocation"
							style="width: 30%; height: 30px; line-height: 30px;">
							<option value="职员">职员</option>
							<option value="学生">学生</option>
							<option value="教师">教师</option>
							<option value="军人">军人</option>
						</select>
					</div>
					<div class="row">
						<label for="C_WebSite">公司网站</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="C_WebSite"
							id="C_WebSite" placeholder="填写你所在企业的主页网址" required>
					</div>
					<br>
					<div class="row">
						企业规模：<select name="C_size" id="C_size"
							style="width: 30%; height: 30px; line-height: 30px;">
							<option value="国营">国营</option>
							<option value="民营">民营</option>
							<option value="外商独资">外商独资</option>
						</select>
					</div>
					<div class="row">
						<label for="C_Tel">电话</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="C_Tel" id="C_Tel"
							required>
					</div>
					<br>
					<div class="row">
						<label for="C_BankName">开户银行名字</label> <input type="text" value=""
							class="input-text-user noPic input-click" name="C_BankName"
							id="C_BankName" required>
					</div>
					<br>
					<div class="row">
						<label for="C_Capital">注册资本</label> <br> <input type="text"
							value="" class="input-text-user noPic input-click"
							style="width: 50px" name="C_Capital" id="C_Capital" required>&nbsp万
					</div>
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
		String sql2 = "select C_Name,C_PostCode,C_Address,C_ConactName,C_Vocation,C_WebSite,C_size,C_Tel,C_BankName,C_Capital from companyuserinfo where username=\'"
				+ username + "\'";
		Connection connection2 = JdbcUtils.getConnection();
		PreparedStatement preparedStatement2 = (PreparedStatement) connection2.prepareStatement(sql2);
		ResultSet rs2 = preparedStatement2.executeQuery();
		while (rs2.next()) {
%>

<body>
	<div class="loginbox">
		<div class="loginbox-title">
			<h3>会员信息</h3>
		</div>
		<div class="login-error"></div>
		<div class="row">
			<label for="username">用户名:</label><span><%=session.getAttribute("adminName")%></span>
		</div>
		<div class="row">
			<label for="C_Name">公司名称:</label> <span><%=rs2.getString(1)%></span>
		</div>
		<br>
		<div class="row">
			<label for="C_PostCode">邮政编码:</label> <span><%=rs2.getString(2)%></span>
		</div>
		<br>

		<div class="row">
			<label for="C_Address">公司地址:</label> <span><%=rs2.getString(3)%></span>
		</div>
		<br>
		<div class="row">
			<label for="C_ConactName">联系人姓名:</label> <span><%=rs2.getString(4)%></span>
		</div>
		<br>
		<div class="row">
			联系人职业：<span><%=rs2.getString(5)%></span>
		</div>
		<div class="row">
			<label for="C_WebSite">公司网站:</label> <span><%=rs2.getString(6)%></span>
		</div>
		<br>
		<div class="row">
			企业规模：<span><%=rs2.getString(7)%></span>
		</div>
		<div class="row">
			<label for="C_Tel">电话:</label> <span><%=rs2.getString(8)%></span>
		</div>
		<br>
		<div class="row">
			<label for="C_BankName">开户银行名字:</label> <span><%=rs2.getString(9)%></span>
		</div>
		<br>
		<div class="row">
			<label for="C_Capital">注册资本:</label> <span><%=rs2.getString(10)%></span>
		</div>
	</div>
</body>
<%
	}
%>
<%
	}
%>
</body>
</html>

