package moe.neptunenoire.web.mysql;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import moe.neptunenoire.web.controller.error.BangumiNotFoundException;
import moe.neptunenoire.web.table.Anime;
import moe.neptunenoire.web.table.Users;

/**
 * 用于数据库查询的接口
 */
@Mapper
public interface MaiKissReo {


    /**
     * =====================================================================
     * 动画查找
     * =====================================================================
     */

    /**
     * 得到所有的动画数据
     */
    @SelectProvider(type = SqlBuilder.class, method = "Anime_FindAllAnime")
    public List<Map<String, Object>> Anime_FindAllAnime();
    /**
     * 根据动画ID查找
     */
    @SelectProvider(type = SqlBuilder.class, method = "Anime_FindByAnimeID")
    public Map<String, Object> Anime_FindByAnimeID(@Param("animeid")String animeid) throws BangumiNotFoundException;
    /**
     * 添加新的动数据
     */
    @InsertProvider(type = SqlBuilder.class, method = "Anime_AddAnime")
    public void Anime_AddAnime(Anime anime);
    /**
     * 从数据库选出N部动画
     * 最新添加
     */
    @SelectProvider(type = SqlBuilder.class, method = "Anime_FindIndexAnime")
    public List<Map<String, Object>> Anime_FindIndexAnime(@Param("limit")int limit);
    /**
     * 从数据库选出8部动画
     * 最多推荐
     */
    /**
     * 从数据库选出8部动画
     * 最多收藏
     */


    /**
     * =====================================================================
     * 用户的查找
     * =====================================================================
     */


    /**
	 * 根据id查询单个用户的信息
	 */
	@SelectProvider(type = SqlBuilder.class, method = "User_FindUserByID")
	public Map<String, Object> User_FindUserByID(@Param("uid")String uid);

	/**
	 * 根据用户名查询用户，可用于密码验证
	 */
    @SelectProvider(type = SqlBuilder.class, method = "User_FindUserByUsername")
	public Map<String, Object> User_FindUserByUsername(@Param("username")String username);

	/**
	 * 用于页面渲染的数据（排除密码）
	 */
    @SelectProvider(type = SqlBuilder.class, method = "User_FindUserByShowByID")
	public Map<String, Object> User_FindUserByShowByID(@Param("uid")String uid);

	/**
	 * 用于页面渲染的数据（排除密码）
	 */
    @SelectProvider(type = SqlBuilder.class, method = "User_FindUserByShowByUsername")
	public Map<String, Object> User_FindUserByShowByUsername(@Param("username")String username);

	/**
	 * (排除密码)
	 * 根据url读取
	 */
    @SelectProvider(type = SqlBuilder.class, method = "User_FindUserByShowByURL")
	public Map<String, Object> User_FindUserByShowByURL(@Param("url")String url);

	/**
	 * 添加用户信息
	 */
	@InsertProvider(type = SqlBuilder.class, method = "User_AddUser")
	public void User_AddUser(Users user);

	/**
	 * 对用户信息进行更新
	 */
	@UpdateProvider(type = SqlBuilder.class, method = "User_UpdataUser")
	public void User_UpdataUser(Users user, @Param("uid")String uid);

	/**
	 * 用户的头像更新
	 */
	@UpdateProvider(type = SqlBuilder.class, method = "User_UpdataPic")
    public void User_UpdataPic(@Param("userpic")String pic, @Param("uid")String uid);


    /**
     * =====================================================================
     * 动画标签
     * =====================================================================
     */

     /**
      * 添加标签信息
      */
    @InsertProvider(type = SqlBuilder.class, method = "Tag_Add")
    public void Tag_Add(@Param("tag_name")String tag_name);
    /**
     * 更新标签信息
     */
    @UpdateProvider(type = SqlBuilder.class, method = "Tag_UpdataTag")
    public void Tag_UpdataTag(@Param("tag_id")String tagid, @Param("tag_name")String tagname);

    public void Tag_Delete();
    /**
     * 查询所有的标签
     */
    @SelectProvider(type = SqlBuilder.class, method = "Tag_FindAll")
    public List<Map<String, Object>> Tag_FindAll();
    /**
     * 查询一个标签
     */
    @SelectProvider(type = SqlBuilder.class, method = "Tag_FindByTagID")
    public Map<String, Object> Tag_FindByTagID(@Param("tag_id")String tagid);


    /**
     * =====================================================================
     * 动画标签
     * =====================================================================
     */



}