package moe.neptunenoire.web.controller;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

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
}
