package xyz.bangumi.mysql.newsql;

import org.apache.ibatis.jdbc.SQL;

import xyz.bangumi.mysql.bean.Anime;

public class AnimeSql {
	public String insert(final Anime anime) {
		return new SQL() {{
			INSERT_INTO("anime");
			
			VALUES("anime_name", "#{anime_name}");
			VALUES("anime_number", "#{anime_number}");
			VALUES("anime_class", "#{anime_class}");
			VALUES("anime_info", "#{anime_info}");
			//===============以上是必写项目===============
			if (anime.getAnime_pic()!=null||anime.getAnime_pic().trim()!="") {
				VALUES("anime_pic", "#{anime_pic}");
			}
			if (anime.getAnime_info2()!=null||anime.getAnime_info2().trim()!="") {
				VALUES("anime_info2", "#{anime_info2}");
			}
			if (anime.getAnime_info_daoyan()!=null||anime.getAnime_info_daoyan().trim()!="") {
				VALUES("anime_info_daoyan", "#{anime_info_daoyan}");
			}
			if (anime.getAnime_info_music()!=null||anime.getAnime_info_music().trim()!="") {
				VALUES("anime_info_music", "#{anime_info_music}");
			}
			if (anime.getAnime_info_de()!=null||anime.getAnime_info_de().trim()!="") {
				VALUES("anime_info_de", "#{anime_info_de}");
			}
			if (anime.getAnime_info_anime()!=null||anime.getAnime_info_anime().trim()!="") {
				VALUES("anime_info_anime", "#{anime_info_anime}");
			}
			if (anime.getAnime_info_site()!=null||anime.getAnime_info_site().trim()!="") {
				VALUES("anime_info_site", "#{anime_info_site}");
			}
			if (anime.getAnime_info_date()!=null||anime.getAnime_info_date().trim()!="") {
				VALUES("anime_info_date", "#{anime_info_date}");
			}
			if (anime.getAnime_info_from()!=null||anime.getAnime_info_from().trim()!="") {
				VALUES("anime_info_from", "#{anime_info_from}");
			}
			if (anime.getAnime_info_op()!=null||anime.getAnime_info_op().trim()!="") {
				VALUES("anime_info_op", "#{anime_info_op}");
			}
			if (anime.getAnime_info_opsonger()!=null||anime.getAnime_info_opsonger().trim()!="") {
				VALUES("anime_info_opsonger", "#{anime_info_opsonger}");
			}
			if (anime.getAnime_info_ed()!=null||anime.getAnime_info_ed().trim()!="") {
				VALUES("anime_info_ed", "#{anime_info_ed}");
			}
			if (anime.getAnime_info_edsonger()!=null||anime.getAnime_info_edsonger().trim()!="") {
				VALUES("anime_info_edsonger", "#{anime_info_edsonger}");
			}
			if (anime.getAnime_tag()!=null||anime.getAnime_tag().trim()!="") {
				VALUES("anime_tag", "#{anime_tag}");
			}
		}}.toString();
	}
	public String selectfromindex() {
		return new SQL() {{
			SELECT("anime_name,anime_pic,anime_id,anime_info");
			FROM("anime");
			ORDER_BY("date_new desc limit 8");
		}}.toString();
	}
	public String updata(final Anime anime) {
		return new SQL() {{
			UPDATE("anime");
			if (anime.getAnime_number()!=null||anime.getAnime_number() != 0) {
				SET("anime_number = #{anime_number}");
			}
			if (anime.getAnime_class()!=null||anime.getAnime_class().trim()!="") {
				SET("anime_class = #{anime_class}");
			}
			if (anime.getAnime_info()!=null||anime.getAnime_info().trim()!="") {
				SET("anime_info = #{anime_info}");
			}
			if (anime.getAnime_name()!=null||anime.getAnime_name().trim()!="") {
				SET("anime_name = #{anime_name}");
			}
			if (anime.getAnime_info2()!=null||anime.getAnime_info2().trim()!="") {
				SET("anime_info2 = #{anime_info2}");
			}
			if (anime.getAnime_info_daoyan()!=null||anime.getAnime_info_daoyan().trim()!="") {
				SET("anime_info_daoyan = #{anime_info_daoyan}");
			}
			if (anime.getAnime_info_music()!=null||anime.getAnime_info_music().trim()!="") {
				SET("anime_info_music = #{anime_info_music}");
			}
			if (anime.getAnime_info_de()!=null||anime.getAnime_info_de().trim()!="") {
				SET("anime_info_de = #{anime_info_de}");
			}
			if (anime.getAnime_info_anime()!=null||anime.getAnime_info_anime().trim()!="") {
				SET("anime_info_anime = #{anime_info_anime}");
			}
			if (anime.getAnime_info_site()!=null||anime.getAnime_info_site().trim()!="") {
				SET("anime_info_site = #{anime_info_site}");
			}
			if (anime.getAnime_info_date()!=null||anime.getAnime_info_date().trim()!="") {
				SET("anime_info_date = #{anime_info_date}");
			}
			if (anime.getAnime_info_from()!=null||anime.getAnime_info_from().trim()!="") {
				SET("anime_info_from = #{anime_info_from}");
			}
			if (anime.getAnime_info_op()!=null||anime.getAnime_info_op().trim()!="") {
				SET("anime_info_op = #{anime_info_op}");
			}
			if (anime.getAnime_info_opsonger()!=null||anime.getAnime_info_opsonger().trim()!="") {
				SET("anime_info_opsonger = #{anime_info_opsonger}");
			}
			if (anime.getAnime_info_ed()!=null||anime.getAnime_info_ed().trim()!="") {
				SET("anime_info_ed = #{anime_info_ed}");
			}
			if (anime.getAnime_info_edsonger()!=null||anime.getAnime_info_edsonger().trim()!="") {
				SET("anime_info_edsonger = #{anime_info_edsonger}");
			}
			if (anime.getAnime_tag()!=null||anime.getAnime_tag().trim()!="") {
				SET("anime_tag = #{anime_tag}");
			}
			WHERE("anime_id = #{animeid}");
		}}.toString();
	}
	public String updatapic() {
		return new SQL() {{
			UPDATE("anime");
			SET("anime_pic = #{anime_pic}");
			WHERE("anime_id = #{anime_id}");
		}}.toString();
	}
}