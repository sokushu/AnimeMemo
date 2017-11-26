/**
 * 用户是否登录
 */
$.ajax({
	url:"/api/api/issign_in",
	type:"GET",
	success:function(data){
		if (data == "true") {
			$('#index_header').hide()
			$('#nav_sign_in').hide()
			$('#nav_sign_up').hide()
		}
	}
})
