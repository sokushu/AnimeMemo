package xyz.bangumi.mysql.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import xyz.bangumi.mysql.newsql.CommentsSql;

@Mapper
public interface CommentsDao {

	/**
	 * 读取用户留言
	 * @return
	 */
	@SelectProvider(type = CommentsSql.class, method = "select0")
	public List<Map<String, Object>> showComm(@Param("uid")String uid);
	
	/**
	 * 添加留言
	 */
	@InsertProvider(type = CommentsSql.class, method = "insert")
	public void addComm(@Param("uid")String uid, @Param("commuid")String commuid, @Param("comm")String comm, @Param("name")String name, @Param("pic")String pic);
	
	/**
	 * 删除留言
	 */
	@DeleteProvider(type = CommentsSql.class, method = "delete")
	public void deleteComm(@Param("commid")String commid);
	
}
