package moe.neptunenoire.web.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moe.neptunenoire.web.mysql.MaiKissReo;

@Component
public class DataSet {

	private static List<Map<String, Object>> usersdata = new ArrayList<>();

	private static Stream<Map<String, Object>> animeData = null;

	private static int SaveDataNum = 1000;

	public static void saveUsersData(Map<String, Object> data) {
		synchronized (usersdata) {
			if (usersdata.size() > SaveDataNum) {
				for (int i = usersdata.size(); i > SaveDataNum; i--) {
					usersdata.remove(0);
				}
			}
			usersdata.add(data);
		}
	}

	public static void saveAnimeData(Map<String, Object> data) {
		synchronized (animeData) {

		}
	}

	public static void setSaveDataNum(int num) {
		SaveDataNum = num;
	}

	@Autowired
	public DataSet(MaiKissReo maiKissReo) {
		initData(maiKissReo);
	}

	private void initData(MaiKissReo maiKissReo) {
		animeData = maiKissReo.Anime_FindAllAnime().stream();
	}

}
