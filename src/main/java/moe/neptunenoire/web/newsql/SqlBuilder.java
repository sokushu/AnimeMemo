package moe.neptunenoire.web.newsql;

import org.apache.ibatis.jdbc.SQL;

import moe.neptunenoire.web.table.Anime;
import moe.neptunenoire.web.table.Users;
import moe.neptunenoire.web.util.StringCheck;


/**
 * 构建新的SQL语句的类
 */
public class SqlBuilder extends StringCheck {
    /**
     * =====================================================================
     * 动画查找
     * =====================================================================
     */
    public String Anime_FindAllAnime(){
        return new SQL(){{
            SELECT("*");
            FROM("anime");
        }}.toString();
    }
    public String Anime_FindByAnimeID(){
        return new SQL(){{
            SELECT("*");
            FROM("anime");
            WHERE("anime_id = #{animeid}");
        }}.toString();
    }
    public String Anime_AddAnime(final Anime anime){
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
    public String Anime_FindIndexAnime(){
        return new SQL(){{
            SELECT("anime_name,anime_pic,anime_id,anime_info");
			FROM("anime");
			ORDER_BY("date_new desc limit #{limit}");
        }}.toString();
    }
    /**
     * =====================================================================
     * 用户的查找
     * =====================================================================
     */
    public String User_FindUserByID(){
        return new SQL(){{
            SELECT("*");
            FROM("users");
            WHERE("uid = #{uid}");
        }}.toString();
    }
    public String User_FindUserByUsername(){
        return new SQL(){{
            SELECT("*");
            FROM("users");
            WHERE("username = #{username}");
        }}.toString();
    }
    public String User_FindUserByShowByID(){
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
    public String User_FindUserByShowByUsername() {
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
	public String User_FindUserByShowByURL() {
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
    public String User_AddUser(final Users users){
        return new SQL(){{
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
    public String User_UpdataUser(final Users users) {
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
    public String User_UpdataPic() {
		return new SQL() {{
			UPDATE("users");
			SET("userpic = #{userpic}");
			WHERE("uid = #{uid}");
		}}.toString();
    }
    /**
     * =====================================================================
     * 动画标签
     * =====================================================================
     */
    public String Tag_Add(){
        return new SQL(){{
            INSERT_INTO("tag");
			VALUES("tag_name", "#{tag_name}");
        }}.toString();
    }
    public String Tag_UpdataTag(){
        return new SQL(){{
            UPDATE("tag");
			SET("tag_name = #{tag_name}");
			WHERE("tag_id = #{tag_id}");
        }}.toString();
    }
    public String Tag_FindAll(){
        return new SQL(){{
            SELECT("*");
            FROM("tag");
        }}.toString();
    }
    public String Tag_FindByTagID(){
        return new SQL(){{
            SELECT("*");
            FROM("tag");
            WHERE("tag_id = #{tag_id}");
        }}.toString();
    }
}