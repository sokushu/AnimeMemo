package xyz.bangumi.mysql.newsql;

import org.apache.ibatis.jdbc.SQL;

public class Tag_AnimeSql {
	public String insert() {
		return new SQL() {{
			INSERT_INTO("tag_anime");
			VALUES("tagid", "#{tagid}");
			VALUES("animeid", "#{animeid}");
		}}.toString();
	}
	public String delete() {
		return new SQL() {{
			DELETE_FROM("tag_anime");
			WHERE("tagid = #{tagid}");
		}}.toString();
	}
	public String updata() {
		return new SQL() {{
			UPDATE("tag_anime");
			SET("animeid = #{animeid}");
			WHERE("tagid = #{tagid}");
		}}.toString();
	}
}
