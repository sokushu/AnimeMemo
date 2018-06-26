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

import moe.neptunenoire.web.controller.error.HomeNotFoundException;
import moe.neptunenoire.web.mysql.MaiKissReo;



@Controller
@EnableAutoConfiguration
public class TheTest {
	public static final String test = "/test/";

	@Autowired
	private RedisTemplate<String, Map<String, Object>> ListMap;

	@Autowired
	private MaiKissReo maiKissReo;
	Map<String, String> map;

	List<String> list;

	@RequestMapping(value = test, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> adada(String id) throws HomeNotFoundException{
		return maiKissReo.User_FindUserByID("710111514556923747")==null?new HashMap<String, Object>() {{
			put("123", "null");
		}}:maiKissReo.User_FindUserByID("710111514556923747");
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public void exit() throws HomeNotFoundException{
		throw new HomeNotFoundException();
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
