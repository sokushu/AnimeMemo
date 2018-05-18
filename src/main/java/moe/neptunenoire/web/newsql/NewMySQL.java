package moe.neptunenoire.web.newsql;

import org.apache.ibatis.jdbc.SQL;

import moe.neptunenoire.web.table.Anime;
import moe.neptunenoire.web.table.Users;
import moe.neptunenoire.web.util.StringCheck;


/**
 * NewMySQL
 */
public class NewMySQL extends StringCheck {
    public String Anime_Add(final Anime anime) {
		return new SQL() {{
			INSERT_INTO("anime");
			
			VALUES("anime_name", "#{anime_name}");
			VALUES("anime_number", "#{anime_number}");
			VALUES("anime_class", "#{anime_class}");
			VALUES("anime_info", "#{anime_info}");
            //===============以上是必写项目===============
            if (isNull(anime.getAnime_info_edsonger()) == false) {
                VALUES("anime_info_edsonger", "#{anime_info_edsonger}");
            }
            if (isNull(anime.getAnime_info_opsonger()) == false) {
                VALUES("anime_info_opsonger", "#{anime_info_opsonger}");
            }
            if (isNull(anime.getAnime_info_ed()) == false) {
                VALUES("anime_info_ed", "#{anime_info_ed}");
            }
            if (isNull(anime.getAnime_info_op()) == false) {
                VALUES("anime_info_op", "#{anime_info_op}");
            }
            if (isNull(anime.getAnime_info_from()) == false) {
                VALUES("anime_info_from", "#{anime_info_from}");
            }
            if (isNull(anime.getAnime_info_date()) == false) {
                VALUES("anime_info_date", "#{anime_info_date}");
            }
            if (isNull(anime.getAnime_info_site()) == false) {
                VALUES("anime_info_site", "#{anime_info_site}");
            }
            if (isNull(anime.getAnime_info_anime()) == false) {
                VALUES("anime_info_anime", "#{anime_info_anime}");
            }
            if (isNull(anime.getAnime_info_de()) == false) {
                VALUES("anime_info_de", "#{anime_info_de}");
            }
            if (isNull(anime.getAnime_info_music()) == false) {
                VALUES("anime_info_music", "#{anime_info_music}");
            }
            if (isNull(anime.getAnime_info_daoyan()) == false) {
                VALUES("anime_info_daoyan", "#{anime_info_daoyan}");
            }
            if (isNull(anime.getAnime_info2()) == false) {
                VALUES("anime_info2", "#{anime_info2}");
            }
		}}.toString();
	}
	public String Anime_selectfromindex() {
		return new SQL() {{
			SELECT("anime_name,anime_pic,anime_id,anime_info");
			FROM("anime");
			ORDER_BY("date_new desc limit 8");
		}}.toString();
	}
	public String Anime_updata(final Anime anime) {
		return new SQL() {{
            UPDATE("anime");
            
			if (isNull(anime.getAnime_number()) == false) {
				SET("anime_number = #{anime_number}");
			}
			if (isNull(anime.getAnime_class()) == false) {
				SET("anime_class = #{anime_class}");
			}
			if (isNull(anime.getAnime_info()) == false) {
				SET("anime_info = #{anime_info}");
			}
			if (isNull(anime.getAnime_name()) == false) {
				SET("anime_name = #{anime_name}");
			}
			if (isNull(anime.getAnime_info2()) == false) {
				SET("anime_info2 = #{anime_info2}");
			}
			if (isNull(anime.getAnime_info_daoyan()) == false) {
				SET("anime_info_daoyan = #{anime_info_daoyan}");
			}
			if (isNull(anime.getAnime_info_music()) == false) {
				SET("anime_info_music = #{anime_info_music}");
            }
			if (isNull(anime.getAnime_info_de()) == false) {
				SET("anime_info_de = #{anime_info_de}");
			}
			if (isNull(anime.getAnime_info_anime()) == false) {
				SET("anime_info_anime = #{anime_info_anime}");
			}
			if (isNull(anime.getAnime_info_site()) == false) {
				SET("anime_info_site = #{anime_info_site}");
			}
			if (isNull(anime.getAnime_info_date()) == false) {
				SET("anime_info_date = #{anime_info_date}");
            }
            if (isNull(anime.getAnime_info_from()) == false) {
				SET("anime_info_from = #{anime_info_from}");
			}
			if (isNull(anime.getAnime_info_op()) == false) {
				SET("anime_info_op = #{anime_info_op}");
			}
			if (isNull(anime.getAnime_info_opsonger()) == false) {
				SET("anime_info_opsonger = #{anime_info_opsonger}");
			}
			if (isNull(anime.getAnime_info_ed()) == false) {
				SET("anime_info_ed = #{anime_info_ed}");
			}
			if (isNull(anime.getAnime_info_edsonger()) == false) {
				SET("anime_info_edsonger = #{anime_info_edsonger}");
			}
			WHERE("anime_id = #{animeid}");
		}}.toString();
	}
	public String Anime_updatapic() {
		return new SQL() {{
			UPDATE("anime");
			SET("anime_pic = #{anime_pic}");
			WHERE("anime_id = #{anime_id}");
		}}.toString();
	}
    public String User_showUser() {
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
	public String User_showUserByUsername() {
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
	public String User_showUserByURL() {
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
	public String User_addUser(final Users users) {
		return new SQL() {{
			INSERT_INTO("users");
			if (isNull(users.getUid()) == false) {
				VALUES("uid", "#{uid}");
			}
			VALUES("username", "#{username}");
			VALUES("password", "#{password}");
			VALUES("email", "#{email}");
			VALUES("url", "#{url}");
			VALUES("pageusername", "#{pageusername}");
		}}.toString();
	}
	public String User_updataUser(final Users users) {
		return new SQL() {{
			UPDATE("users");
			if (isNull(users.getPassword()) == false) {
				SET("password = #{password}");
			}
			if (isNull(users.getEmail()) == false) {
				SET("email = #{email}");
			}
			if (isNull(users.getUrl()) == false) {
				SET("url = #{url}");
			}
			if (isNull(users.getInfo()) == false) {
				SET("info = #{info}");
			}
			if (isNull(users.getPageusername()) == false) {
				SET("pageusername = #{pageusername}");
			}
			WHERE("uid = #{uid}");
		}}.toString();
	}
	public String User_updataPic() {
		return new SQL() {{
			UPDATE("users");
			SET("userpic = #{userpic}");
			WHERE("uid = #{uid}");
		}}.toString();
    }
    public String User_Anime_insert() {
		return new SQL() {{
			INSERT_INTO("user_anime");
			
			VALUES("uid", "#{uid}");
			VALUES("animeid", "#{animeid}");
			VALUES("allnumber", "#{allnumber}");
		}}.toString();
	}
	public String User_Anime_updata() {
		return new SQL() {{
			UPDATE("user_anime");
			
			SET("number = #{number}");
			
			SET("article = #{article}");
			WHERE("uid = #{uid} and animeid = #{animeid}");
		}}.toString();
	}
	public String User_Anime_updatanumber(){
		return new SQL(){{
			UPDATE("user_anime");
			SET("number = #{number}");
			WHERE("uid = #{uid} and animeid = #{animeid}");
		}}.toString();
    }
    public String Tag_insert() {
		return new SQL() {{
			INSERT_INTO("tag");
			VALUES("tag_name", "#{tag_name}");
		}}.toString();
	}
	public String Tag_updata() {
		return new SQL() {{
			UPDATE("tag");
			SET("tag_name = #{tag_name}");
			WHERE("tag_id = #{tag_id}");
		}}.toString();
    }
    public String Tag_Anime_insert() {
		return new SQL() {{
			INSERT_INTO("tag_anime");
			VALUES("tagid", "#{tagid}");
			VALUES("animeid", "#{animeid}");
		}}.toString();
	}
	public String Tag_Anime_delete() {
		return new SQL() {{
			DELETE_FROM("tag_anime");
			WHERE("tagid = #{tagid}");
		}}.toString();
	}
	public String Tag_Anime_updata() {
		return new SQL() {{
			UPDATE("tag_anime");
			SET("animeid = #{animeid}");
			WHERE("tagid = #{tagid}");
		}}.toString();
    }
    public String ShotUrl_AddUrl(){
        return new SQL(){{
            INSERT_INTO("shoturl");
            VALUES("url", "#{url}");
            VALUES("id", "#{id}");
        }}.toString();
    }
    public String ShotUrl_FindUrl(){
        return new SQL(){{
            SELECT("url");
            FROM("shoturl");
            WHERE("id = #{id}");
        }}.toString();
    }
}