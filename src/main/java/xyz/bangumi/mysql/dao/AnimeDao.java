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
 */
// 标志为 Mybatis 的 Mapper
@Mapper
public interface AnimeDao {
    /**
     * 根据动画id寻找
     * @param animeid
     * @return
     */
    @Select("SELECT * FROM anime WHERE anime_id = #{animeid}")
    //返回 Map 结果集
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