package moe.neptunenoire.web.database;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.web.controller.error.BangumiNotFoundException;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.table.Users;
import moe.neptunenoire.web.util.StringUtil;

public class ReoKissMai extends DataSet {

	private StringUtil stringUtil = new StringUtil();

	/**
	 *
	 * @param maiKissReo
	 * @param redis
	 */
	public ReoKissMai(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		super(maiKissReo, redis);
	}

	@Override
	public List<Map<String, Object>> Anime_FindAllAnime() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> Anime_FindByAnimeID(String animeid) throws BangumiNotFoundException {
		int animeID;
		try {
			animeID = Integer.parseInt(animeid);
		} catch (Exception e) {
			throw new BangumiNotFoundException(e.getMessage());
		}
		Map<String, Object> data = getAnimeOne(animeID);
		if (stringUtil.isNull(data)) {
			data = maiKissReo.Anime_FindByAnimeID(animeid);
		}
		if (stringUtil.isNull(data)) {
			throw new BangumiNotFoundException("非常抱歉，没有找到"+animeid);
		}
		return data;
	}

	@Override
	public void Anime_AddAnime(moe.neptunenoire.web.table.Anime anime) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public List<Map<String, Object>> Anime_FindIndexAnime(int limit) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> User_FindUserByID(String uid) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> User_FindUserByUsername(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> User_FindUserByShowByID(String uid) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> User_FindUserByShowByUsername(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> User_FindUserByShowByURL(String url) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void User_AddUser(Users user) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void User_UpdataUser(Users user, String uid) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void User_UpdataPic(String pic, String uid) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void Tag_Add(String tag_name) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void Tag_UpdataTag(String tagid, String tagname) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void Tag_Delete() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public List<Map<String, Object>> Tag_FindAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> Tag_FindByTagID(String tagid) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}



}
