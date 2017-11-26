package xyz.bangumi.mysql.newsql;

import org.apache.ibatis.jdbc.SQL;

public class TagSql {
	public String insert() {
		return new SQL() {{
			INSERT_INTO("tag");
			VALUES("tag_name", "#{tag_name}");
		}}.toString();
	}
	public String updata() {
		return new SQL() {{
			UPDATE("tag");
			SET("tag_name = #{tag_name}");
			WHERE("tag_id = #{tag_id}");
		}}.toString();
	}
}
