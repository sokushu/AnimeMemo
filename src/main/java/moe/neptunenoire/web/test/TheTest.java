package moe.neptunenoire.web.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@EnableAutoConfiguration
public class TheTest {
	public static final String test = "/test/";

	@Autowired
	private RedisTemplate<String, Map<String, Object>> ListMap;

	Map<String, String> map;

	List<String> list;

	@RequestMapping(value = test, method = RequestMethod.GET)
	@ResponseBody
	public String adada(String id){
		return map.get(id);
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public void exit(){

	}

	@RequestMapping(value = "/muda/", method = RequestMethod.GET)
	@ResponseBody
	public long muda(String w) {
		Map<String, Object> m = new HashMap<>();
		m.put("test", "Hello World!!");
		ListMap.opsForValue().set("test", m);
		return ListMap.opsForList().size("AABB");
	}
}
