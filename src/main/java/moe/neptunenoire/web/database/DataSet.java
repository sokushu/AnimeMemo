package moe.neptunenoire.web.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import moe.neptunenoire.web.mysql.MaiKissReo;

/**
 *
 * @author M
 *
 */
@Component
public class DataSet {

	private static RedisTemplate<String, List<Map<String, Object>>> redis;
	
	private static MaiKissReo maiKissReo;
	/**
	 * 保存用户数据
	 * @param data
	 */
	public static void saveUsersData(Map<String, Object> data) {
		redis.opsForList().set("", 0, null);
	}

	/**
	 *
	 * @param filter
	 * @return
	 */
	public static Map<String, Object> getUser(String ID){
		return null;
	}

	/**
	 * 得到用户数
	 * @return
	 */
	public static int getUserNum() {
		return 0;
	}

	/**
	 * 得到数据的数量
	 * @return
	 */
	public static int getAnimeNum() {
		return 0;
	}

	/**
	 * 保存一个动画数据
	 * @param data 动画的数据
	 */
	public static void saveAnimeData(Map<String, Object> data) {
//		synchronized (animeData) {
//			animeData.add(data);
//		}
	}

	/**
	 * 得到动画集合
	 * @param filter 过滤规则
	 * @return
	 */
	public static List<Map<String, Object>> getAnimeData(Predicate<? super Map<String, Object>> filter){
		return null;//animeData.stream().filter(filter).collect(Collectors.toList());
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
	public DataSet(MaiKissReo maiKissReo, RedisTemplate<String, List<Map<String, Object>>> redis) {
		initData(maiKissReo, redis);
	}

	/**
	 * 数据初始化
	 * @param maiKissReo
	 */
	private void initData(MaiKissReo maiKissReo, RedisTemplate<String, List<Map<String, Object>>> redis) {
		DataSet.maiKissReo = maiKissReo;
		DataSet.redis = redis;
	}
}
