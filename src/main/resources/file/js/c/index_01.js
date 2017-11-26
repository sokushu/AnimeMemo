/**
 * index页面代码头部部分
 */
/*
 * 首页搜索块的代码
 * 效果：点击搜索按钮，如果没有输入就会弹出提示框
 */
$("#div_search").click( function () {
	 if($("#div_search_text").val().trim()==""){
		 $('[data-toggle="popover"]').popover()
		 $("#div_search_text").focus()
		 $("#div_search_text").prop("placeholder", "什么都没有填写呢(0.0)")
		 return false
	 }
 });
/*
 * 登录模块的表单验证
 */
$('#denglu').click(function() {
    if($("#inputusername").val().trim()==""||$("#inputPassword").val().trim()==""){
    	$("#alert_windows_in").html("<br><div class='alert alert-info alert-dismissible fade in' role='alert' id='alert_windows0'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>×</span></button><strong><span class='glyphicon glyphicon-remove-sign'></span>登录失败</strong><br>OH！您还没有输入用户名或密码。</div>"); 
    	return false;
    }else{
    	$("#denglu").html("登录中...")
    	$("#denglu").attr({"disabled":"disabled"})
   		$.ajax({
         		 type:"POST",
         		 url:"/ajax/",
         		 async:true,
         		 data:{
         			 "from" : "index",
	               	 "username" : $("#inputusername").val().trim(),
	                 "password" : $("#inputPassword").val().trim()
         		 }, 
         		 datetype:"TEXT",
          	 
          	   success:function(data, textStatus) {
               var resultData = data;
		            if ("true" == resultData) {
		            	$("#index_login").submit()
					}else{
						$("#alert_windows_in").html("<br><div class='alert alert-warning alert-dismissible fade in' role='alert' id='alert_windows1'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>×</span></button><strong><span class='glyphicon glyphicon-remove-sign'></span>登录失败</strong><br>OH！发生了错误，您输入了错误的用户名或密码。</div>");
						$("#denglu").html("登录")
						$("#denglu").removeAttr("disabled")
					}
              }
        });
}});
