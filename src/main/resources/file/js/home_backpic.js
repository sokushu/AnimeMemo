/**
 * 
 */
var theurl = $('#input_url').val()
		$.ajax({
			url:"/id/getbackimg",
			type:"GET",
			data:{
				"url":theurl
			},
			success:function(data){
				$('#div_picdf').attr("style","background-color:rgb(64, 64, 64);background-image:url('"+ data +"');")
			}
		})