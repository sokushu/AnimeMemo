package moe.neptunenoire.web.controller;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.web.controller.error.BangumiNotFoundException;
import moe.neptunenoire.web.database.DataSet;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.util.StringUtil;

public class Bangumi {

	/** 数据库字段 */
	private MaiKissReo maiKissReo;
	/** 内存数据库操作 */
	private DataSet dataSet;
	/** 字符工具类 */
	private StringUtil stringUtil = new StringUtil();

	public Bangumi(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		this.maiKissReo = maiKissReo;
		this.dataSet = new DataSet(maiKissReo, redis);
	}

	/**
	 *
	 * @param BangumiID
	 * @return
	 * @throws BangumiNotFoundException
	 */
	public Map<String, Object> showBangumi(String BangumiID) throws BangumiNotFoundException {

		int bangumiid;
		try {
			/* 转换成数字 */
			bangumiid = Integer.parseInt(BangumiID);
		} catch (Exception e) {
			throw new BangumiNotFoundException();
		}

		/* 数据库查询动画资料 */
		Map<String, Object> bangumiData = dataSet.getAnimeOne(bangumiid);

		/* 验证是否为空 */
		if (stringUtil.isNull(bangumiData)) {
			bangumiData = maiKissReo.Anime_FindByAnimeID(BangumiID);
			dataSet.saveAnimeData(bangumiData);
		}
		/* 验证是否为空 */
		if (stringUtil.isNull(bangumiData)) {
			throw new BangumiNotFoundException();
		}

		return bangumiData;
	}
}
