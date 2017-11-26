/**
 * 对输入信息进行简单的验证，提交数据库
 */
$('#addbangumi_submit').click(function() {
	if ($('#anime_name_id').val().trim()==""||$('#anime_info_id').val().trim()==""||$('#anime_number_id').val().trim()=="") {
		$("#addbangumi_submit").html("执行中...")
    	$("#addbangumi_submit").attr({"disabled":"disabled"})
		$('#div_infoma').html("<br><div class='alert alert-info alert-dismissible fade in' role='alert' id='alert_windows0'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>×</span></button><strong><span class='glyphicon glyphicon-remove-sign'></span>您还有未填写的必写字段</strong><br>动画的名称，集数和简介是必写的哦</div>")
		$("#addbangumi_submit").html("提交")
		$("#addbangumi_submit").removeAttr("disabled")
		return false
	}else{
		$("#addbangumi_submit").html("执行中...")
    	$("#addbangumi_submit").attr({"disabled":"disabled"})
		$('#addbangumi_from').submit()
	}
})