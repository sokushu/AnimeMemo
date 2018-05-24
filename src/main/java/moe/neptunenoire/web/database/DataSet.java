package moe.neptunenoire.web.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moe.neptunenoire.web.mysql.MaiKissReo;

/**
 *
 * @author M
 *
 */
@Component
public class DataSet {

	/**
	 * 用户的数据
	 */
	private static List<Map<String, Object>> usersData = new ArrayList<>();

	/**
	 * 启动时从数据读取的动画数据
	 */
	private static List<Map<String, Object>> animeData = null;

	/**
	 * 保存的数据数量
	 */
	private static int SaveDataNum = 1000;

	/**
	 * 保存用户数据
	 * @param data
	 */
	public static void saveUsersData(Map<String, Object> data) {
		synchronized (usersData) {
			if (usersData.size() > SaveDataNum) {
				for (int i = usersData.size(); i > SaveDataNum; i--) {
					usersData.remove(0);
				}
			}
			usersData.add(data);
		}
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
	 * 设置保存数量
	 * @param num
	 */
	public static void setSaveDataNum(int num) {
		SaveDataNum = num;
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
