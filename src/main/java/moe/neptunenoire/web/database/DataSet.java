package moe.neptunenoire.web.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moe.neptunenoire.web.mysql.MaiKissReo;
import redis.clients.jedis.Jedis;

/**
 *
 * @author M
 *
 */
@Component
public class DataSet {

	private static Jedis jedis = new Jedis("localhost");

	private static List<String> tableName = new ArrayList<>();

	/**
	 * 保存用户数据
	 * @param data
	 */
	public static void saveUsersData(String key, Map<String, Object> data) {
		final Map<String, String> map = new HashMap<>();
		data.keySet().forEach(val->{
			String newVal = String.valueOf(data.get(val));
			map.put(val, newVal);
		});
		jedis.hmset(key, map);
	}

	/**
	 *
	 * @param filter
	 * @return
	 */
	public static Map<String, Object> getUser(Predicate<? super Map<String, Object>> filter){
		List<Map<String, Object>> list = usersData.stream().filter(filter).collect(Collectors.toList());
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 得到用户数
	 * @return
	 */
	public static int getUserNum() {
		return usersData.size();
	}

	/**
	 * 得到数据的数量
	 * @return
	 */
	public static int getAnimeNum() {
		return animeData.size();
	}

	/**
	 * 保存一个动画数据
	 * @param data 动画的数据
	 */
	public static void saveAnimeData(Map<String, Object> data) {
		synchronized (animeData) {
			animeData.add(data);
		}
	}

	/**
	 * 得到动画集合
	 * @param filter 过滤规则
	 * @return
	 */
	public static List<Map<String, Object>> getAnimeData(Predicate<? super Map<String, Object>> filter){
		return animeData.stream().filter(filter).collect(Collectors.toList());
	}

	/**
	 * 得到一部动画
	 * @param filter 过滤规则
	 * @return
	 */
	public static Map<String, Object> getAnimeOne(Predicate<? super Map<String, Object>> filter){
		return getAnimeData(filter).get(0);
	}

	/**
	 * 数据操作类初始化
	 * @param maiKissReo
	 */
	@Autowired
	public DataSet(MaiKissReo maiKissReo) {
		initData(maiKissReo);
	}

	/**
	 * 数据初始化
	 * @param maiKissReo
	 */
	private void initData(MaiKissReo maiKissReo) {
		animeData = maiKissReo.Anime_FindAllAnime();
	}
}
