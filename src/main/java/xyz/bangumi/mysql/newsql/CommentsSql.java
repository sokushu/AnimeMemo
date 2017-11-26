package xyz.bangumi.mysql.newsql;

import org.apache.ibatis.jdbc.SQL;

public class CommentsSql {
	public String insert() {
		return new SQL() {{
			INSERT_INTO("comments");
			VALUES("uid", "#{uid}");
			VALUES("commuid", "#{commuid}");
			VALUES("comm", "#{comm}");
			VALUES("name", "#{name}");
			VALUES("pic", "#{pic}");
		}}.toString();
	}
	public String select0() {
		return new SQL() {{
			SELECT("*");
			FROM("comments");
			WHERE("uid = #{uid}");
			ORDER_BY("data_new desc");
		}}.toString();
	}
	public String delete() {
		return new SQL() {{
			DELETE_FROM("comments");
			WHERE("commid = #{commid}");
		}}.toString();
	}
}
