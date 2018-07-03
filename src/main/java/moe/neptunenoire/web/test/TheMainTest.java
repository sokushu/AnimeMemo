package moe.neptunenoire.web.test;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.serializer.GroupSerializer;

public class TheMainTest {

	private String str = "";
	private void print() {
		System.out.println("Hello World" + str);
	}

	public static void main(String[] args) {
		try {
			DB db = DBMaker.memoryDB().make();

			BTreeMap<String, String> bTreeMap = db.treeMap("map").keySerializer(GroupSerializer.STRING).valueSerializer(GroupSerializer.STRING).create();

			bTreeMap.put("Hello", "Hello00");

			System.out.println(bTreeMap.get("Hello"));

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
