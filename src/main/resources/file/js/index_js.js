/*
登录
*/
function sign_in(){
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
			var mes = data
			if (mes == true) {
				$('#form_sign_in').submit();
			}else{
				$('#showmessage').html("用户或密码错误")
				return false;
			}
		}
	})
}

var html =
"<div class='col s12 m6 l3'><div class='card small' style='overflow: visible;'>" +
"<div class='card-image waves-effect waves-block waves-light'>" +
"<img class='activator' src='";
var html1 =
"'>" +
"</div>" +
"<div class='card-content'>" +
"<span class='card-title activator grey-text text-darken-4'>";
var htmlt =
"<i class='material-icons right'>more_vert</i></span>" +
"<p><a href='";
var html2 =
"'>";
var html3 =	
"</a></p>" +
"</div>" +
"<div class='card-reveal' style='display: none; transform: translateY(0px);'>" +
"<span class='card-title grey-text text-darken-4'>";
var html4 =
"<i class='material-icons right'>close</i></span>" +
"<p>" ;
var html5 =
"</p>" +
"</div>" +
"<div class='card-action'>" +
"<a href='";
var html6 =
"'>";
var html7 =
"</a>" +
"</div></div></div>";
/*
 * 获取首页8个最新添加动画的数据
 */
$.ajax({
	url:"/api/anime/indexanime",
	type:"GET",
	success:function(data){
		$.each(data, function(index, i){
			$('#div_indexanime').append(html + i.anime_pic + html1 + i.anime_name + htmlt + "/bangumi/"+i.anime_id + html2 + i.anime_name + html3 + i.anime_name + html4 + i.anime_info + html5 + "/bangumi/"+i.anime_id + html6 + "番组详情页" + html7)
		})
	}
})
