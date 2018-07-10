package moe.neptunenoire.web.database;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.MainRun;
import moe.neptunenoire.MainRun.FileType;
import moe.neptunenoire.web.mysql.MaiKissReo;

/**
 *
 * @author M
 *
 */
abstract class DataSet implements MaiKissReo{

	private RedisTemplate<String, Map<String, Object>> redis;
	// https://www.cnblogs.com/nfcm/p/7833032.html
	protected MaiKissReo maiKissReo;

	private static DB db = DBMaker.memoryDB().make();
	
	private static DB fileMapDB = DBMaker.fileDB(MainRun.getFilePath("imgPath", FileType.db)).make();
	
	private static BTreeMap<String, Map<String, Object>> bTreeMap = db.treeMap("theMyblog").keySerializer(MapDB.STRING).valueSerializer(MapDB.MAP).create();
	
	private static BTreeMap<String, Map<String, Object>> imgPathMap = db.treeMap("imgPathMap").keySerializer(MapDB.STRING).valueSerializer(MapDB.MAP).createOrOpen();
	
	public static enum DataType{
		TOKU,
		Anime,
		User,
		Blog,
		Tag,
		Img,
	}

	/**
	 * 数据操作类初始化
	 * @param maiKissReo
	 */
	public DataSet(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		this.maiKissReo = maiKissReo;
		this.redis = redis;
	}

	/**
	 * 保存数据
	 * @param data
	 */
	public void saveData(DataType type, long key, Map<String, Object> data) {
		redis.opsForList().set(type.name(), key, data);
	}

	public void saveData(DataType type, String key, Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		sb.append(type.name()).append("-").append(key);
		redis.opsForValue().set(sb.toString(), data);
	}


	/**
	 * 得到一条数据
	 * @param filter
	 * @return
	 */
	public Map<String, Object> getData(DataType type, long key){
		return redis.opsForList().index(type.name(), key);
	}

	/**
	 * 
	 * @param type
	 * @param key
	 * @return
	 */
	public Map<String, Object> getData(DataType type, String key){
		StringBuilder sb = new StringBuilder();
		sb.append(type.name()).append("-").append(key);
		return redis.opsForValue().get(sb.toString());
	}

	/**
	 * 得到数
	 * @return
	 */
	public long getDataSize(DataType type) {
		return redis.opsForList().size(type.name());
	}

	/**
	 *
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> getAllData(DataType type) {
		return redis.opsForList().range(type.name(), 0, 5000);
	}

	/**
	 * 得到集合
	 * @param filter 过滤规则
	 * @return
	 */
	public List<Map<String, Object>> getFilterAllData(DataType type, Predicate<? super Map<String, Object>> filter){
		return redis.opsForList().range(type.name(), 0, 5000).stream().filter(filter).collect(Collectors.toList());
	}
	
	/**
	 * 
	 */
	public static void MapDBExit() {
		bTreeMap.close();
		db.close();
		imgPathMap.close();
		fileMapDB.close();
	}
}
