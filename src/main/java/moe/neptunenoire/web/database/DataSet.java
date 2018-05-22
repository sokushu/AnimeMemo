package moe.neptunenoire.web.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moe.neptunenoire.web.mysql.MaiKissReo;

@Component
public class DataSet {

	/**
	 *
	 */
	private static List<Map<String, Object>> usersData = new ArrayList<>();

	/**
	 *
	 */
	private static Stream<Map<String, Object>> animeData = null;

	/**
	 *
	 */
	private static List<Map<String, Object>> newAnimeData = new ArrayList<>();

	/**
	 *
	 */
	private static int SaveDataNum = 1000;

	/**
	 *
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
	 * @return
	 */
	public static int getUserNum() {
		return usersData.size();
	}

	/**
	 *
	 * @return
	 */
	public static int getAnimeNum() {
		return (int)animeData.count();
	}

	/**
	 *
	 * @param data
	 */
	public static void saveAnimeData(Map<String, Object> data) {
		synchronized (animeData) {
			newAnimeData.add(data);
		}
	}

	/**
	 *
	 * @param filter
	 * @return
	 */
	public static List<Map<String, Object>> getAnimeData(Predicate<? super Map<String, Object>> filter){
		return animeData.filter(filter).collect(Collectors.toList());
	}

	/**
	 *
	 * @param filter
	 * @return
	 */
	public static Map<String, Object> getAnimeOne(Predicate<? super Map<String, Object>> filter){
		return getAnimeData(filter).get(0);
	}

	/**
	 *
	 * @param num
	 */
	public static void setSaveDataNum(int num) {
		SaveDataNum = num;
	}

	/**
	 *
	 * @param maiKissReo
	 */
	@Autowired
	public DataSet(MaiKissReo maiKissReo) {
		initData(maiKissReo);
	}

	/**
	 *
	 * @param maiKissReo
	 */
	private void initData(MaiKissReo maiKissReo) {
		animeData = maiKissReo.Anime_FindAllAnime().stream();
	}
}
