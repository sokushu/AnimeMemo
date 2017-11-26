/**
 * 
 */
/*
 * 各个页面的上面导航栏的搜索使用的js
 */
$("#div_search").click( function () {
	if($("#div_search_text").val().trim()==""){
		$('[data-toggle="popover"]').popover()
		$("#div_search_text").focus()
		$("#div_search_text").prop("placeholder", "什么都没有填写呢(0.0)")
		return false
	}
});