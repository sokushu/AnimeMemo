package xyz.bangumi.mysql.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageDao {
	
	/**
	 * 我发送过的信息
	 * @return
	 */
	@Select("")
	public Map<String, Object> returnByfromID();
	/**
	 * 我收到过的信息
	 * @return
	 */
	public Map<String, Object> returnByToID();
	/**
	 * 发送信息的列表
	 * @return
	 */
	public List<Map<String, Object>> returnByfromIDList();
	/**
	 * 收到信息的列表
	 * @return
	 */
	public List<Map<String, Object>> returnByToIDList();
}
