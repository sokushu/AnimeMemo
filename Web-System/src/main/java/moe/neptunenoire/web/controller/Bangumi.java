package moe.neptunenoire.web.controller;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.web.controller.error.BangumiNotFoundException;
import moe.neptunenoire.web.database.ReoKissMai;
import moe.neptunenoire.web.mysql.MaiKissReo;

public class Bangumi {

	/** 数据库操作 */
	private ReoKissMai reoKissMai;

	public Bangumi(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		reoKissMai = new ReoKissMai(maiKissReo, redis);
	}

	/**
	 *
	 * @param BangumiID
	 * @return
	 * @throws BangumiNotFoundException
	 */
	public Map<String, Object> showBangumi(String BangumiID) throws BangumiNotFoundException {

		Map<String, Object> bangumiData = reoKissMai.Anime_FindByAnimeID(BangumiID);

		return bangumiData;
	}
}
