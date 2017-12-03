package xyz.bangumi.mysql.dao;

import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import xyz.bangumi.mysql.bean.Users;
import xyz.bangumi.mysql.newsql.UserSql;

@Mapper
public interface UserDao {
	
	/**
	 * 根据id查询单个用户的信息
	 * @param uid
	 * @return
	 */
	@Select("SELECT * FROM users WHERE uid = #{uid}")
	public Map<String, Object> findUser(@Param("uid")String uid);
	
	/**
	 * 根据用户名查询用户，可用于密码验证
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM users WHERE username = #{username}")
	public Map<String, Object> findUserByUsername(@Param("username")String username);
	
	/**
	 * 用于页面渲染的数据（排除密码）
	 * @return
	 */
	@SelectProvider(type = UserSql.class, method = "showUser")
	public Map<String, Object> showUser(@Param("uid")String uid);
	
	/**
	 * 用于页面渲染的数据（排除密码）
	 * @return
	 */
	@SelectProvider(type = UserSql.class, method = "showUserByUsername")
	public Map<String, Object> showUserByUsername(@Param("username")String username);
	
	/**
	 * (排除密码)
	 * 根据url读取
	 * @param url
	 * @return
	 */
	@SelectProvider(type = UserSql.class, method = "showUserByURL")
	public Map<String, Object> showUserByURL(@Param("url")String url);
	
	/**
	 * 添加用户信息
	 * @param user
	 */
	@InsertProvider(type = UserSql.class, method = "addUser")
	public void addUser(Users user);
	
	/**
	 * 对用户信息进行更新
	 * @param user
	 */
	@UpdateProvider(type = UserSql.class, method = "updataUser")
	public void updataUser(Users user, @Param("uid")String uid);
	
	/**
	 * 用户的头像更新
	 * @param pic
	 * @param uid
	 */
	@UpdateProvider(type = UserSql.class, method = "updataPic")
	public void updataPic(@Param("userpic")String pic, @Param("uid")String uid);
}
