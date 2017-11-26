package xyz.bangumi.mysql.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import xyz.bangumi.mysql.newsql.User_AnimeSql;

@Mapper
public interface User_AnimeDao {

	/**
	 * 用户订阅一部动画
	 */
	@InsertProvider(type = User_AnimeSql.class, method = "insert")
	public void add(@Param("animeid") String animeid, @Param("uid")String uid, @Param("allnumber")String allnumber);
	
	/**
	 * 用户对订阅的动画修改
	 */
	@UpdateProvider(type = User_AnimeSql.class, method = "updata")
	public void updata(@Param("number") String number, @Param("article")String article, @Param("uid")String uid, @Param("animeid")String animeid);
	
	/**
	 * 查询用户订阅的所有动画
	 * @param uid
	 * @return
	 */
	@Select("SELECT * FROM user_anime WHERE uid = #{uid}")
	public List<Map<String, Object>> selectAll(@Param("uid")String uid);
	
	/**
	 * 删除用户订阅的一部动画
	 */
	@Delete("DELETE FROM user_anime WHERE uid = #{uid} and animeid = #{animeid}")
	public void delete(@Param("uid")Integer uid, @Param("animeid")String animeid);

	/**
	 * 查询用户正在看的动画
	 */
	@Select("SELECT * FROM user_anime WHERE number < allnumber and uid = #{uid}")
	public List<Map<String, Object>> findWatching(@Param("uid")String uid);
	
	/**
	 * 查询用户看完的动画
	 */
	@Select("SELECT * FROM user_anime WHERE number = allnumber and uid = #{uid}")
	public List<Map<String, Object>> findWatched(@Param("uid")String uid);
	
	/**
	 * home页展示
	 * @return
	 */
	@Select("SELECT * FROM user_anime WHERE number < allnumber and uid = #{uid} ORDER BY anime_updata desc limit 3")
	public List<Map<String, Object>> findWatchingBy3(@Param("uid")String uid);
	
	/**
	 * home页展示
	 * @return
	 */
	@Select("SELECT * FROM user_anime WHERE number = allnumber and uid = #{uid} ORDER BY anime_updata desc limit 3")
	public List<Map<String, Object>> findWatchedBy3(@Param("uid")String uid);
	
	/**
	 * 查询用户正在的动画的数量
	 * @param uid
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM user_anime where uid = #{uid} and number < allnumber")
	public int UserWatchingAnime(@Param("uid")String uid);
	
	/**
	 * 查询用户看过的动画
	 * @param uid
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM user_anime where uid = #{uid} and number = allnumber")
	public int UserWatchedAnime(@Param("uid")String uid);
}
