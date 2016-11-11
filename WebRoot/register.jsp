<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<link href="resources/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
</head>
<script type="text/javascript">
//在键盘按下并释放及提交后验证提交表单
$().ready(function() {
	
var signupForm = $("#signupForm").validate({
	errorPlacement: function(error, element) {
		$( element )
			.closest( "form" )
				.find( "label[for='" + element.attr( "id" ) + "']" )
					.append( error );
	},
	errorElement: "span",
	
	  rules: {
	    username: {
	      minlength: 2,
	      remote:{
              type:"POST",
              url:"registerCheckUsernameAction",             
              data:{
                veryCode:function(){return $("#username").val();}
              } 
             } 
	    },
	    password:{
	    	minlength:6,
	    	maxlength:20
	    },
	    passwordAgain:{
	    	equalTo:"#password"
	    },
	    question:{
	    	maxlength:36
	    },
	    answer:{
	    	minlength:6,
	    	maxlength:20
	    },
	    safecode:{
	    	minlength:6,
	    	maxlength:20
	    },
	    safecodeAgain:{
	    	equalTo:"#safecode"
	    },
	    email:{
	    	email:true,
	    	remote:{
	              type:"POST",
	              url:"registerCheckEmailAction",             
	              data:{
	                veryCode:function(){return $("#email").val();}
	              } 
	             } 
	    }
	 
	  },
	  
	  messages: {
	    username: {
	      minlength: "(用户名必需由多个字符组成)",
	      remote:"(该用户名已被注册)"
	    },
	    password:{
	      minlength: "(密码长度为6~20位，可以由字母，数字，特殊字符组成)",
	      maxlength:"(密码长度为6~20位，可以由字母，数字，特殊字符组成)"
	    },
	    passwordAgain:{
	    	equalTo:"(密码需保持一致)"
	    },
	    question:{
	    	maxlength:"(问题长度不大于36个字符)"
	    },
	    answer:{
	    	minlength:"(答案长度为6~20位，区分大小写)",
	    	maxlength:"(答案长度为6~20位，区分大小写)"
	    },
	    safecode:{
	    	minlength:"(安全码长度为6~20位，区分大小写)",
	    	maxlength:"(安全码长度为6~20位，区分大小写)"
	    },
	    safecodeAgain:{
	    	equalTo:"(安全码需保持一致)"
	    },
	    email:{
	    	email:"(请输入有效的邮箱)",
	    	remote:"(该邮箱已被注册)"
	    }
	  }
});
$("#signupForm").validate({
    submitHandler:function(form){
        alert("提交事件!");   
        form.submit();
    }    
});
});
</script>
<body class="loginbody">
<div class="dataEye">
	<div class="loginbox registbox">
		<div class="login-content reg-content">
			<div class="loginbox-title">
				<h3>注册</h3>
			</div>
			<form id="signupForm" action="registerSuccessAction" method="post" ENCTYPE="multipart/form-data">
			<div class="login-error"></div>
			<div class="row"  align="center">
				注册类型  <select name="shenfen" id="type">
					<option value="个人会员">个人会员</option>
					<option value="企业会员">企业会员</option>
				</select>
			</div>
			<div class="row">
				<label for="username">用户名</label>
				<input type="text" value="" class="input-text-user noPic input-click" name="username" id="username" placeholder="用户名" required>
			</div>
			<div class="row">
				<label class="field">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp请填写您的基本资料</label>
			</div>
			<div class="row">
				<label for="password">密码</label>
				<input type="password" value="" class="input-text-password noPic input-click" name="password" id="password" placeholder="密码" required>
			</div>
			<br>
			<div class="row">
				<label for="passwordAgain">确认密码</label>
				<input type="password" value="" class="input-text-password noPic input-click" name="passwordAgain" id="passwordAgain" placeholder="确认密码" required>
			</div>
			<br>
			<div class="row">
				安全问题
				<select id="question" name="question" style=" width:30%; height:20px;line-height:30px; " required>
					<option value="你的母亲是？">你的母亲是？</option>
					<option value="你的父亲是？">你的父亲是？</option>
					<option value="你的生日是什么时候？">你的生日是什么时候？</option>
				</select>
			</div>
			<div class="row">
				<label for="answer">答案</label>
				<input type="text" value="" class="input-text-user noPic input-click" name="answer" id="answer" placeholder="安全问题答案" required>
			</div>
			<br>
			<div class="row">
				<label for="safecode">安全码</label>
				<div><font color="blue">(安全码是您找回密码的重要路径，设定后不能修改)</font></div>
				<input type="password" value="" class="input-text-user noPic input-click" name="safecode" id="safecode" placeholder="安全码，用于忘记密码找回时使用" required>
			</div>
			<br>
			<br>
			<div class="row">
				<label for="safecodeAgain">确认安全码</label>
				<input type="password" value="" class="input-text-user noPic input-click" name="safecodeAgain" id="safecodeAgain" placeholder="安全码确认" required>
			</div>
			<br>
			<div class="row">
				<label for="img">头像地址</label>
				<input type="file" value="" class="input-text-user " name="img" id="img" required>
			</div>
			<br>
			<div class="row">
				<label for="email">电子邮件</label>
				<input type="text" value="" class="input-text-user noPic input-click" name="email" id="email" placeholder="电子邮件" required>
			</div>
			<div class="row btnArea">
				<input type="submit"  value="注册" class="login-btn" id="submit">
			</div>
			</form>
		</div>
		<div class="go-regist">
			已有帐号,请<a href="login.jsp" class="link">登录</a>
		</div>
	</div>
</div>

</body>
</html>

