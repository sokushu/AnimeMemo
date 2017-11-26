package xyz.bangumi.mysql.newsql;

import org.apache.ibatis.jdbc.SQL;

public class MessageSql {
	public String insert() {
		return new SQL() {{
			INSERT_INTO("");
		}}.toString();
	}
}
