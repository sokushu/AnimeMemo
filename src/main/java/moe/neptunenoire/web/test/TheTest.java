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
import moe.neptunenoire.web.database.ReoKissMai;
import moe.neptunenoire.web.mysql.MaiKissReo;



@Controller
@EnableAutoConfiguration
public class TheTest {
	public static final String test = "/test/";

	private RedisTemplate<String, Map<String, Object>> ListMap;

	private MaiKissReo maiKissReo;
	
	private ReoKissMai reoKissMai;
	Map<String, String> map;

	List<String> list;
	
	@Autowired
	public TheTest(RedisTemplate<String, Map<String, Object>> ListMap, MaiKissReo maiKissReo) {
		this.ListMap = ListMap;
		this.maiKissReo = maiKissReo;
		reoKissMai = new ReoKissMai(maiKissReo, ListMap);
	}

	@ResponseBody
	@RequestMapping(value = test, method = RequestMethod.GET)
	public String adada(String id) {
		try {
			
			if (id.equals("0")) {
				reoKissMai.setImgMapDB("test", "testHelloWorld");
			}
			if (id.equals("1")) {
				return reoKissMai.getImgMapDB("test");
			}
			
			return "请选择ID";
			
		} catch (Exception e) {
			// TODO: handle exception
			return "ERROR";
		}
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
