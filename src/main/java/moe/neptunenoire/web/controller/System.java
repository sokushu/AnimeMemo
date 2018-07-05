package moe.neptunenoire.web.controller;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.web.mysql.MaiKissReo;

public class System {

	public System(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {

	}

	/**
	 *
	 * @return
	 */
	private String getTotalMemory() {
		long totalMemory = Runtime.getRuntime().totalMemory();
		return String.valueOf(totalMemory);
	}

	private String getFreeMemory() {
		long freeMemory = Runtime.getRuntime().freeMemory();
		return String.valueOf(freeMemory);
	}

}
