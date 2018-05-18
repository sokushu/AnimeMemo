package moe.neptunenoire.web.mysql;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import moe.neptunenoire.web.table.Anime;
import moe.neptunenoire.web.table.Users;


/**
 * MySQL
 */
@Mapper
public interface MySQL {
    /**
     * ===============================================================
     * 
     * ===============================================================
     */
    /**
     * 根据动画ID寻找
     */
    @Select("SELECT * FROM anime WHERE anime_id = #{animeid}")
    public Map<String, Object> Anime_FindByAnimeID(@Param("animeid") String animeid);
    /**
     * 新增加动画
     */
    // @InsertProvider(type = NewMySQL.class, method = "insert")
    public void Anime_AddAnime(Anime anime);
    /**
     * 在数据库中选出最新添加的N部动画
     */
    // @SelectProvider(type = NewMySQL.class, method = "selectfromindex")
    public List<Map<String, Object>> Anime_FindIndexAnime();
    /**
     * 对动画数据进行修改
     */
    // @UpdateProvider(type = NewMySQL.class, method = "updata")
    public void Anime_UpData(@Param("animeid")String animeid, Anime anime);
    /**
     * 返回所有的动画列表
     */
    @Select("SELECT * FROM anime")
    public List<Map<String, Object>> Anime_ReturnAllAnime();
     /**
     * 搜索动画
     */
    @Select("SELECT * FROM anime WHERE anime_name LIKE '%${keyword}%'")
    public List<Map<String, Object>> Anime_SearchAnime(@Param("keyword")String ketword);
    /**
     * 对图片进行更新
     */
    // @UpdateProvider(type = NewMySQL.class, method = "updatapic")
    public void Anime_UpDataPic(@Param("anime_pic")String pic, @Param("anime_id")String animeid);

    /**
     * ===============================================================
     * 
     * ===============================================================
     */

    /**
	 * 根据id查询单个用户的信息
	 */
	@Select("SELECT * FROM users WHERE uid = #{uid}")
	public Map<String, Object> User_FindUser(@Param("uid")String uid);
	
	/**
	 * 根据用户名查询用户，可用于密码验证
	 */
	@Select("SELECT * FROM users WHERE username = #{username}")
	public Map<String, Object> User_FindUserByUsername(@Param("username")String username);
	
	/**
	 * 用于页面渲染的数据（排除密码）
	 */
	// @SelectProvider(type = NewMySQL.class, method = "showUser")
	public Map<String, Object> User_FindUserByShow(@Param("uid")String uid);
	
	/**
	 * 用于页面渲染的数据（排除密码）
	 */
	// @SelectProvider(type = NewMySQL.class, method = "showUserByUsername")
	public Map<String, Object> User_FindUserByUsernameByShow(@Param("username")String username);
	
	/**
	 * (排除密码)
	 * 根据url读取
	 */
	// @SelectProvider(type = NewMySQL.class, method = "showUserByURL")
	public Map<String, Object> User_FindUserByURL(@Param("url")String url);
	
	/**
	 * 添加用户信息 
	 */
	// @InsertProvider(type = NewMySQL.class, method = "addUser")
	public void User_AddUser(Users user);
	
	/**
	 * 对用户信息进行更新
	 */
	// @UpdateProvider(type = NewMySQL.class, method = "updataUser")
	public void User_UpdataUser(Users user, @Param("uid")String uid);
	
	/**
	 * 用户的头像更新
	 */
	// @UpdateProvider(type = NewMySQL.class, method = "updataPic")
    public void User_UpdataPic(@Param("userpic")String pic, @Param("uid")String uid);
    
    /**
     * ===============================================================
     * 
     * ===============================================================
     */
    /**
	 * 添加标签信息
	 */
	// @InsertProvider(type = NewMySQL.class, method = "insert")
	public void Tag_add(@Param("tag_name")String name);
	
	/**
	 * 更新标签信息
	 */
	// @UpdateProvider(type = NewMySQL.class, method = "updata")
	public void Tag_updata(@Param("tag_id")String tagid, @Param("tag_name")String tagname);
	
	/**
	 * 删除标签
	 */
	@Delete("")
	public void Tag_delete();
	
	/**
	 * 查询标签
	 */
	@Select("SELECT * FROM tag")
	public List<Map<String, Object>> Tag_findAll();
	
	/**
	 * 查询一个标签
	 */
	@Select("SELECT * FROM tag WHERE tag_id = #{tag_id}")
    public Map<String, Object> Tag_findByTagID(@Param("tag_id")String tagid);
    /**
     * ===============================================================
     * 
     * ===============================================================
     */

     /**
	 * 插入数据
	 */
	// @InsertProvider(type = NewMySQL.class, method = "insert")
	public void Tag_Anime_insert(@Param("tagid")Integer tagid, @Param("animeid")String animeid);
	
	/**
	 * 删除
	 */
	// @DeleteProvider(type = NewMySQL.class, method = "delete")
	public void Tag_Anime_delete(@Param("tagid")String tagid);
	
	/**
	 * 更新
	 */
	// @UpdateProvider(type = NewMySQL.class, method = "updata")
	public void Tag_Anime_updata(@Param("animeid")String animeid, @Param("tagid")String tagid);
	
	/**
	 * 查询全部
	 */
	@Select("SELECT * FROM tag_anime WHERE tagid = #{tagid}")
	public List<Map<String, Object>> Tag_Anime_findAll(@Param("tagid")String tagid);
	
	/**
	 * 查找一个
	 */
	@Select("SELECT * FROM tag_anime WHERE tagid = #{tagid}")
    public Map<String, Object> Tag_Anime_findOne(@Param("tagid")String tagid);
    
    /**
     * ===============================================================
     * 
     * ===============================================================
     */

     /**
	 * 用户订阅一部动画
	 */
	// @InsertProvider(type = NewMySQL.class, method = "insert")
	public void add(@Param("animeid") String animeid, @Param("uid")String uid, @Param("allnumber")String allnumber);
	
	/**
	 * 用户对订阅的动画修改
	 */
	// @UpdateProvider(type = NewMySQL.class, method = "updata")
	public void updata(@Param("number") String number, @Param("article")String article, @Param("uid")String uid, @Param("animeid")String animeid);
	
	/**
	 * 用户对动画集数的修改
	 */
	// @UpdateProvider(type = NewMySQL.class, method = "updatanumber")
	public void updatabumber(@Param("animeid")String animeid, @Param("number")String number, @Param("uid")String uid);
	
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
    /**
     * ===============================================================
     * 
     * ===============================================================
     */
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
	 * 查询用户所有订阅的动画
	 */
	@Select("SELECT b.anime_id,b.anime_info,b.anime_name,b.anime_number,b.anime_class,b.anime_pic,a.number FROM user_anime a,anime b WHERE a.animeid = b.anime_id and uid = #{uid}")
	public List<Map<String, Object>> findWatchAnime(@Param("uid")String uid);

	/**
	 * 寻找正在看的动画的信息
	 * @return
	 */
	@Select("SELECT b.anime_id,b.anime_info,b.anime_name,b.anime_number,b.anime_class,b.anime_pic,a.number FROM user_anime a,anime b WHERE a.animeid = b.anime_id and uid = #{uid} and a.allnumber > number order by data_updata desc limit 3")
	public List<Map<String, Object>> findWatchingAnimeInfoBy3(@Param("uid")String uid);
	
	/**
	 * 寻找看过的动画的信息
	 * @return
	 */
	@Select("SELECT b.anime_id,b.anime_info,b.anime_name,b.anime_number,b.anime_class,b.anime_pic,a.number FROM user_anime a,anime b WHERE a.animeid = b.anime_id and uid = #{uid} and a.allnumber = number order by data_updata desc limit 3")
	public List<Map<String, Object>> findWatchedAnimeInfoBy3(@Param("uid")String uid);
	
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

	/**
	 * 寻找这部动画是否是某个用户订阅的
	 */
	@Select("SELECT b.anime_id,b.anime_info,b.anime_name,b.anime_number,b.anime_class,b.anime_pic,a.number FROM user_anime a,anime b WHERE a.animeid = b.anime_id and uid = #{uid} and b.anime_id = #{animeid}")
	public Map<String, Object> findIsdingyue(@Param("uid")String uid, @Param("animeid")String animeid);
}