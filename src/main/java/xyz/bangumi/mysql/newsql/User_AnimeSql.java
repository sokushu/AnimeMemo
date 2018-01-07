package xyz.bangumi.mysql.newsql;

import org.apache.ibatis.jdbc.SQL;

public class User_AnimeSql {
	public String insert() {
		return new SQL() {{
			INSERT_INTO("user_anime");
			
			VALUES("uid", "#{uid}");
			VALUES("animeid", "#{animeid}");
			VALUES("allnumber", "#{allnumber}");
		}}.toString();
	}
	public String updata() {
		return new SQL() {{
			UPDATE("user_anime");
			
			SET("number = #{number}");
			
			SET("article = #{article}");
			WHERE("uid = #{uid} and animeid = #{animeid}");
		}}.toString();
	}
	public String updatanumber(){
		return new SQL(){{
			UPDATE("user_anime");
			SET("number = #{number}");
			WHERE("uid = #{uid} and animeid = #{animeid}");
		}}.toString();
	}
}
