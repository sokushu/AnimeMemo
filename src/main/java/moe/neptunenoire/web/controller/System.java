package moe.neptunenoire.web.controller;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.web.database.ReoKissMai;
import moe.neptunenoire.web.mysql.MaiKissReo;

public class System {

	public System(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {

	}

	/**
	 *
	 * @return
	 */
	
	public String getTotalMemory() {
		long totalMemory = Runtime.getRuntime().totalMemory();
		
		return String.valueOf(totalMemory);
	}

	/**
	 *
	 * @return
	 */
	public String getFreeMemory() {
		long freeMemory = Runtime.getRuntime().freeMemory();
		return String.valueOf(freeMemory);
	}
	
	public void systemExit() {
		// 关闭MapDB
		ReoKissMai.MapDBExit();
		// 关闭程序
		java.lang.System.exit(0);
	}

}
