package xyz.bangumi.mysql.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import xyz.bangumi.mysql.newsql.Tag_AnimeSql;

@Mapper
public interface Tag_AnimeDao {
	
	/**
	 * 插入数据
	 */
	@InsertProvider(type = Tag_AnimeSql.class, method = "insert")
	public void insert(@Param("tagid")Integer tagid, @Param("animeid")String animeid);
	
	/**
	 * 删除
	 */
	@DeleteProvider(type = Tag_AnimeSql.class, method = "delete")
	public void delete(@Param("tagid")String tagid);
	
	/**
	 * 更新
	 */
	@UpdateProvider(type = Tag_AnimeSql.class, method = "updata")
	public void updata(@Param("animeid")String animeid, @Param("tagid")String tagid);
	
	/**
	 * 查询全部
	 */
	@Select("SELECT * FROM tag_anime WHERE tagid = #{tagid}")
	public List<Map<String, Object>> findAll(@Param("tagid")String tagid);
	
	/**
	 * 查找一个
	 */
	@Select("SELECT * FROM tag_anime WHERE tagid = #{tagid}")
	public Map<String, Object> findOne(@Param("tagid")String tagid);
}
