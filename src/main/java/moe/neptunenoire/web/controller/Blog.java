package moe.neptunenoire.web.controller;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.web.database.ReoKissMai;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.util.FileReadAndLoad;
import moe.neptunenoire.web.util.StringUtil;

public class Blog {

	/** 数据库操作 */
	private ReoKissMai reoKissMai;
	/** 字符工具类 */
	private StringUtil stringUtil = new StringUtil();
	/** 文件的读取与写入 */
	private FileReadAndLoad readAndLoad = new FileReadAndLoad();

	/**
	 *
	 * @param maiKissReo
	 * @param redis
	 */
	public Blog(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		reoKissMai = new ReoKissMai(maiKissReo, redis);
	}

	/**
	 *
	 */
	public void showBlog() {

	}
}
