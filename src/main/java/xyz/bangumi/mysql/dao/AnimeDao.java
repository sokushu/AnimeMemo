package xyz.bangumi.mysql.dao;

import org.apache.ibatis.annotations.Param;
import xyz.bangumi.mysql.bean.Anime;
import xyz.bangumi.mysql.newsql.AnimeSql;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

/**
 * 动画
 * @author miri
 *
 */
@Mapper // 标志为 Mybatis 的 Mapper

public interface AnimeDao {
    /**
     * 根据动画id寻找
     * @param animeid
     * @return
     */
    @Select("SELECT * FROM anime WHERE anime_id = #{animeid}")
    //返回 Map 结果集
    @Results({
    	@Result(property = "anime_id", column = "anime_id"),
        @Result(property = "anime_name", column = "anime_name"),
        @Result(property = "anime_number", column = "anime_number"),
        @Result(property = "anime_class", column = "anime_class"),
        @Result(property = "anime_pic", column = "anime_pic"),
        @Result(property = "anime_info", column = "anime_info"),
        @Result(property = "anime_info2", column = "anime_info2"),
        @Result(property = "anime_info_daoyan", column = "anime_info_daoyan"),
        @Result(property = "anime_info_music", column = "anime_info_music"),
        @Result(property = "anime_info_de", column = "anime_info_de"),
        @Result(property = "anime_info_pic", column = "anime_info_pic"),
        @Result(property = "anime_info_site", column = "anime_info_site"),
        @Result(property = "anime_info_date", column = "anime_info_date"),
        @Result(property = "anime_info_from", column = "anime_info_from"),
        @Result(property = "anime_info_op", column = "anime_info_op"),
        @Result(property = "anime_info_opsonger", column = "anime_info_opsonger"),
        @Result(property = "anime_info_ed", column = "anime_info_ed"),
        @Result(property = "anime_info_edsonger", column = "anime_info_edsonger"),
        @Result(property = "anime_tag", column = "anime_tag"),
    })
    public Map<String, Object> findByAnimeID(@Param("animeid") String animeid);
//=========================================================================================================================    
    /**
     * 根据动画名寻找
     */
    @Select("SELECT * FROM anime WHERE anime_name = #{animename}")
    public List<Map<String, Object>> findBYAnimeName(@Param("animename")String animename);
//=========================================================================================================================        
    /**
     * 新增加动画
     */
    /*
     * sql语句构建器
     */
    @InsertProvider(type = AnimeSql.class, method = "insert")
    public void insert(Anime anime);
//=========================================================================================================================       
    /**
     * 在数据库中选出最新添加的N部动画<br>
     * <b>（首页用）</b>
     * @return
     */
    @SelectProvider(type = AnimeSql.class, method = "selectfromindex")
    public List<Map<String, Object>> findByData();
//=========================================================================================================================    
    /**
     * 对数据进行修改
     * @param animeid
     * @param anime
     */
    @UpdateProvider(type = AnimeSql.class, method = "updata")
    public void updata(@Param("animeid")String animeid, Anime anime);
//=========================================================================================================================
    /**
     * 删除一个动画数据
     */
    @Delete("DELETE FROM anime WHERE anime_id = #{anime_id}")
    public void delete(@Param("anime_id")String animeid);
//=========================================================================================================================
    @Select("SELECT * FROM anime")
    public List<Map<String, Object>> returnAllAnime();
    
//=========================================================================================================================
    /**
     * 搜索
     * @param ketword
     * @return
     */
    @Select("SELECT * FROM anime WHERE anime_name LIKE '%${keyword}%'")
    public List<Map<String, Object>> searchAnime(@Param("keyword")String ketword);
    
    /**
     * 升级
     * @param pic
     * @param animeid
     */
    @UpdateProvider(type = AnimeSql.class, method = "updatapic")
    public void updataPic(@Param("anime_pic")String pic, @Param("anime_id")String animeid);
}