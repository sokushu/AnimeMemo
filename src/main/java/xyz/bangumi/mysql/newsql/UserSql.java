package xyz.bangumi.mysql.newsql;

import org.apache.ibatis.jdbc.SQL;

import xyz.bangumi.mysql.bean.Users;

public class UserSql {
	public String showUser() {
		return new SQL() {{
			SELECT("uid");
			SELECT("username");
			SELECT("email");
			SELECT("url");
			SELECT("userpic");
			SELECT("info");
			SELECT("pageusername");
			SELECT("backpic");
			FROM("users");
			WHERE("uid = #{uid}");
		}}.toString();
	}
	public String showUserByUsername() {
		return new SQL() {{
			SELECT("uid");
			SELECT("username");
			SELECT("email");
			SELECT("url");
			SELECT("userpic");
			SELECT("info");
			SELECT("pageusername");
			SELECT("backpic");
			FROM("users");
			WHERE("username = #{username}");
		}}.toString();
	}
	public String showUserByURL() {
		return new SQL() {{
			SELECT("uid");
			SELECT("username");
			SELECT("email");
			SELECT("url");
			SELECT("userpic");
			SELECT("info");
			SELECT("pageusername");
			SELECT("backpic");
			FROM("users");
			WHERE("url = #{url}");
		}}.toString();
	}
	public String addUser(final Users users) {
		return new SQL() {{
			INSERT_INTO("users");
			VALUES("username", "#{username}");
			VALUES("password", "#{password}");
			VALUES("email", "#{email}");
			VALUES("url", "#{url}");
			VALUES("pageusername", "#{pageusername}");
		}}.toString();
	}
	public String updataUser(final Users users) {
		return new SQL() {{
			UPDATE("users");
			try {
				users.getPassword();
				if (users.getPassword()!="") {
					SET("password = #{password}");
				}
			} catch (Exception e) {}
			try {
				users.getEmail();
				if (users.getEmail()!="") {
					SET("email = #{email}");
				}
			} catch (Exception e) {}
			try {
				users.getUrl();
				if (users.getUrl()!="") {
					SET("url = #{url}");
				}
			} catch (Exception e) {}
			try {
				users.getInfo();
				if (users.getInfo()!="") {
					SET("info = #{info}");
				}
			} catch (Exception e) {}
			try {
				users.getPageusername();
				if (users.getPageusername()!="") {
					SET("pageusername = #{pageusername}");
				}
			} catch (Exception e) {}
			WHERE("uid = #{uid}");
		}}.toString();
	}
	public String updataPic() {
		return new SQL() {{
			UPDATE("users");
			SET("userpic = #{userpic}");
			WHERE("uid = #{uid}");
		}}.toString();
	}
}
