package moe.neptunenoire.web.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.web.controller.error.BlogNotFoundException;
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
	 * @throws BlogNotFoundException
	 */
	public Map<String, Object> showBlog(String blogid) throws BlogNotFoundException {

		Map<String, Object> data = reoKissMai.Blog_FindBlogByID(blogid);

		/* 得到的将会是文件名 */
		String articleurl = data.get("articleurl").toString();

		String blog;
		try {
			blog = readAndLoad.ReadTextForString(articleurl);
		} catch (IOException e) {
			throw new BlogNotFoundException();
		}
		// 将内容替换
		if (!stringUtil.isNull(blog)) {
			data.put("articleurl", blog);
		}
		return data;
	}
}
