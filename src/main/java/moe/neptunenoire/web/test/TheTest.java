package moe.neptunenoire.web.test;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import moe.neptunenoire.InfoData;
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

	@RequestMapping(value = test, method = RequestMethod.POST)
	public void adada(String id, MultipartFile file0) throws HomeNotFoundException{
		try {
			File file = InfoData.getStaticPath();
			file0.transferTo(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@RequestMapping(value = test, method = RequestMethod.GET)
	public String test() {
		return "test";
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
