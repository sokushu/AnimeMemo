/**
 * 
 */
$.ajax({
	type:"GET",
	url:"/api/anime/getall",
	async:true,
	success:function(data){
		$.each(data, function(index, i){
			alert(i.anime_name)
			i.anime_pic
			i.anime_id
		})
	}
})