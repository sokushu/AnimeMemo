/**
 * 
 */
var theurl = $('#input_url').val()

var html1 = 
"<div class='col s12'>"+
"<div class='col s1'>"+
	"<img class='responsive-img' src='"; html2 = "'/>"+
"</div>" +
"<div class='col s11'>"+
	"<p>"; html3 = "</p>"+
	"<p>"; html4 = "</p>"+
"</div>"+
"</div>";

$('#comments_go').click(function(){
	var real = "/id/" + theurl + "/comm"
	$('#icon_prefix244').trigger('autoresize')
	var tes = $('#icon_prefix244').val()
	
	$.ajax({
		url: real,
		type:"POST",
		data:{
			comm:tes
		},
		success:function(data){
			$.ajax({
				url: real,
				type:"GET",
				success:function(data){
					$('#home_comments').html("")
					$.each(data.commdata, function(index, i){
						showcomm(1)
						var tes = $('#icon_prefix244').val('')
					})
				}
			})
		}
	})
})
function showcomm(pagenum){
	var real = "/id/" + theurl + "/comm"
	$.ajax({
		url: real,
		type:"GET",
		data:{
			page:pagenum
		},
		success:function(data){
			$('#home_comments').html("")
			$.each(data.commdata, function(index, i){
				$('#home_comments').append(html1 + i.pic + 
						html2 + i.name + html3 + i.comm + html4)
			})
			if (data.isnext == false) {
				$('#showinfo').html("当前第" + data.pagelast + "页，共" + data.pagelast + "页")
			}else{
				$('#showinfo').html("当前第" + data.pagenow + "页，共" + data.pagelast + "页")
			}
			if (data.ispre == false) {
				$('#showinfo').html("当前第1页，共" + data.pagelast + "页")
			}else{
				$('#showinfo').html("当前第" + data.pagenow + "页，共" + data.pagelast + "页")
			}
		}
	})
}

$(document).ready(function(){
	var k = 1
	showcomm(k)
	$('#pagepre').click(function(){
		k = k - 1
		showcomm(k)
	})
	$('#pagenext').click(function(){
		k = k + 1
		showcomm(k)
	})
	
})


	
	
	
	
	