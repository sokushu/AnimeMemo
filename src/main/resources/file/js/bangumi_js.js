/**
 * 
 */

var theid = $('#inputofanimeid').val()
$('#addbangumitouser').click(function(){
	$.ajax({
		url:"/bangumi/" + theid ,
		type:"POST",
		data:theid,
		success:function(data){
			if (data == "true") {
				$('#addbangumitouser').html('已定阅')
			}
		}
	})
})