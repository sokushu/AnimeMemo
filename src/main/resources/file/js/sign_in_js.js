/**
 * 
 */
$('#login').click(function(){
	
	var username = $('#username').val().trim()
	var password = $('#password').val().trim()
	if (username == ""||password == "") {
		$('#showmessage').html("请完整填写用户名和密码")
		return false;
	}
	$.ajax({
		url:"/ajaxs/sign_in",
		type:"POST",
		data:{
			username:username,
			password:password
		},
		success:function(data){
			if (data == true) {
				$('#form_sign_in').submit()
			}else{
				$('#showmessage').html("用户或密码错误")
				return false;
			}
		}
	})
})


