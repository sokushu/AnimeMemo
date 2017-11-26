package xyz.bangumi.mysql.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import xyz.bangumi.mysql.newsql.TagSql;


@Mapper
public interface TagDao {
	
	/**
	 * 添加标签信息
	 */
	@InsertProvider(type = TagSql.class, method = "insert")
	public void add(@Param("tag_name")String name);
	
	/**
	 * 更新标签信息
	 */
	@UpdateProvider(type = TagSql.class, method = "updata")
	public void updata(@Param("tag_id")String tagid, @Param("tag_name")String tagname);
	
	/**
	 * 删除标签
	 */
	@Delete("")
	public void delete();
	
	/**
	 * 查询标签
	 */
	@Select("SELECT * FROM tag")
	public List<Map<String, Object>> findAll();
	
	/**
	 * 查询一个标签
	 */
	@Select("SELECT * FROM tag WHERE tag_id = #{tag_id}")
	public Map<String, Object> findOne(@Param("tag_id")String tagid);
}
