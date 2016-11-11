$(document).ready(function(){
	
	//获取JS传递的语言参数
	var utils = new Utils();
	var args = utils.getScriptArgs();	
	
	
	//隐藏Loading/注册失败 DIV
	$(".loading").hide();
	$(".login-error").hide();
	registError = $("<label class='error repeated'></label>");
	
	//加载国际化语言包资源
	utils.loadProperties(args.lang);
	
	//输入框激活焦点、移除焦点
	jQuery.focusblur = function(focusid) {
		var focusblurid = $(focusid);
		var defval = focusblurid.val();
		focusblurid.focus(function(){
			var thisval = $(this).val();
			if(thisval==defval){
				$(this).val("");
			}
		});
		focusblurid.blur(function(){
			var thisval = $(this).val();
			if(thisval==""){
				$(this).val(defval);
			}
		});
	 
	};
	/*下面是调用方法*/
	$.focusblur("#email");
	
	//获取表单验证对象[填写验证规则]
	var validate = $("#signupForm").validate({
		rules: {
			email: {
				required: true,
				email: true
			},
			password: {
				required: true,
				minlength: 4,
				maxlength: 16
			},
			passwordAgain: {
				required: true,
				minlength: 4,
				maxlength: 16,
				equalTo: "#password"
			},
			contact: {
				required: true
			},
			company: {
				required: true
			},
			tel: {
				required: true,
				digits:true
			},
			qq: {
				required: true,
				digits:true
			}
		},
		messages: {
			email: {
				required: $.i18n.prop("Form.PleaseInputEmail"),
				email: $.i18n.prop("Form.PleaseInputCorrectEmail")
			},
			password: {
				required: $.i18n.prop("Form.PleaseInputPassword"),
				minlength: jQuery.format($.i18n.prop("Form.PasswordFormat")),
				maxlength: jQuery.format($.i18n.prop("Form.PasswordFormatMax"))
			},
			passwordAgain: {
				required: $.i18n.prop("Form.PasswordAgain"),
				minlength: jQuery.format($.i18n.prop("Form.PasswordFormat")),
				maxlength: jQuery.format($.i18n.prop("Form.PasswordFormatMax")),
				equalTo: jQuery.format($.i18n.prop("Form.PasswordDifferent"))
			},
			contact: {
				required: $.i18n.prop("Form.PleaseInputContact")
			},
			company: {
				required: $.i18n.prop("Form.PleaseInputCompany")
			},
			tel: {
				required: $.i18n.prop("Form.PleaseInputTel"),
				digits: $.i18n.prop("Form.IncorrectFormatTel")
			},
			qq: {
				required: $.i18n.prop("Form.PleaseInputQQ"),
				digits: $.i18n.prop("Form.IncorrectFormatQQ")
			}
		}
	});
	
	
	//输入框激活焦点、溢出焦点的渐变特效
	if($("#email").val()){
		$("#email").prev().fadeOut();
	};
	$("#email").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#email").blur(function(){
		if(!$("#email").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#password").val()){
		$("#password").prev().fadeOut();
	};
	$("#password").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#password").blur(function(){
		if(!$("#password").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#passwordAgain").val()){
		$("#passwordAgain").prev().fadeOut();
	};
	$("#passwordAgain").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#passwordAgain").blur(function(){
		if(!$("#passwordAgain").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#contact").val()){
		$("#contact").prev().fadeOut();
	};
	$("#contact").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#contact").blur(function(){
		if(!$("#contact").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#company").val()){
		$("#company").prev().fadeOut();
	};
	$("#company").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#company").blur(function(){
		if(!$("#company").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#tel").val()){
		$("#tel").prev().fadeOut();
	};
	$("#tel").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#tel").blur(function(){
		if(!$("#tel").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#qq").val()){
		$("#qq").prev().fadeOut();
	};
	$("#qq").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#qq").blur(function(){
		if(!$("#qq").val()){
			$(this).prev().fadeIn();
		};		
	});
	
	//ajax提交注册信息
	$("#submit").bind("click", function(){
		regist(validate);
	});
	
	$("body").each(function(){
		$(this).keydown(function(){
			if(event.keyCode == 13){
				regist(validate);
			}
		});
	});
	
});

function regist(validate){	
	//校验Email, password，校验如果失败的话不提交
	if(validate.form()){
		if($("#checkBox").attr("checked")){
			var md5 = new MD5();
			$.ajax({
				url: "./user/regist.do",
				type: "post",
				data: {
					userID: $("#email").val(),
					password: md5.MD5($("#password").val()),
					userName: $("#contact").val(),
					companyName: $("#company").val(),
					tel: $("#tel").val(),
					QQ: $("#qq").val()
					
				},
				dataType: "json",
				beforeSend: function(){
					$('.loading').show();
				},
				success: function(data){
					$('.loading').hide();
					if(data.hasOwnProperty("code")){
						if(data.code == 0){
							//注册成功
							window.location.href = "registOk.jsp?email="+$('#email').val();
						}else if(data.code == 1){
							//数据库链接失败
							$(".login-error").html($.i18n.prop("Error.Exception"));
						}else if(data.code == 2){
							//参数传递失败
							$(".login-error").show();
							$(".login-error").html($.i18n.prop("Error.ParameterError"));
						}else if(data.code == 3){
							//公司已经被注册
							$("#company").addClass("error");
							$("#company").after(registError);						
							$("#company").next("label.repeated").text($.i18n.prop("Error.CompaniesAlreadyExists"));
							registError.show();
						}else if(data.code == 4){
							//邮箱已经被注册
							$("#email").addClass("error");
							$("#email").after(registError);
							$("#email").next("label.repeated").text($.i18n.prop("Error.EmailAlreadyExists"));
							registError.show();
						}else{
							//系统错误
							$(".login-error").html($.i18n.prop("Error.SysError"));
						}
					}
				}
			});
		}else{
			//勾选隐私政策和服务条款
			$(".login-error").show();
			$(".login-error").html($.i18n.prop("Error.ReadAndAgree"));
		}
	}
}

var Utils = function(){};

Utils.prototype.loadProperties = function(lang){
	jQuery.i18n.properties({// 加载资浏览器语言对应的资源文件
		name:'ApplicationResources',
		language: lang,
		path:'resources/i18n/',
		mode:'map',
		callback: function() {// 加载成功后设置显示内容
		} 
	});	
};

Utils.prototype.getScriptArgs = function(){//获取多个参数
    var scripts=document.getElementsByTagName("script"),
    //因为当前dom加载时后面的script标签还未加载，所以最后一个就是当前的script
    script=scripts[scripts.length-1],
    src=script.src,
    reg=/(?:\?|&)(.*?)=(.*?)(?=&|$)/g,
    temp,res={};
    while((temp=reg.exec(src))!=null) res[temp[1]]=decodeURIComponent(temp[2]);
    return res;
};
