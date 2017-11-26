package xyz.bangumi.mysql.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
 * 多表查询
 * @author miri
 *
 */
@Mapper
public interface SELECT {
	
	/**
	 * 寻找正在看的动画的信息
	 * @return
	 */
	@Select("SELECT b.anime_id,b.anime_info,b.anime_name,b.anime_number,b.anime_class,b.anime_pic,a.number FROM user_anime a,anime b WHERE a.animeid = b.anime_id and uid = #{uid} and a.allnumber > number")
	public List<Map<String, Object>> findWatchingAnimeInfo(@Param("uid")String uid);
	
	/**
	 * 寻找看过的动画的信息
	 * @return
	 */
	@Select("SELECT b.anime_id,b.anime_info,b.anime_name,b.anime_number,b.anime_class,b.anime_pic,a.number FROM user_anime a,anime b WHERE a.animeid = b.anime_id and uid = #{uid} and a.allnumber = number")
	public List<Map<String, Object>> findWatchedAnimeInfo(@Param("uid")String uid);
	
	/**
	 * 寻找动画的标签信息
	 * @return
	 */
	public List<Map<String, Object>> findAnimeTagInfo();
	
	/**
	 * 寻找标签下的动画信息
	 * @return
	 */
	public List<Map<String, Object>> findTag_AnimeInfo();
}
