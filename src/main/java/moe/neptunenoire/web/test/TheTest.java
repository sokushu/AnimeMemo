package moe.neptunenoire.web.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.web.util.FileReadAndLoad;
import redis.clients.jedis.Jedis;


@Controller
@EnableAutoConfiguration
public class TheTest {
	public static final String test = "/test/";

	public String id = null;

	public String time = String.valueOf(Calendar.getInstance().getTimeInMillis());

	FileReadAndLoad fileutil = new FileReadAndLoad("D:\\Test");


	Map<String, String> map;

	List<String> list;

	public TheTest() {
		 try {
			map = fileutil.ReadProperties("proper");
			list = fileutil.ReadTextByLine("proper");
		} catch (IOException e) {}
	}

	@RequestMapping(value = test, method = RequestMethod.GET)
	@ResponseBody
	public String adada(String id){
		return map.get(id);
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public void exit() {
		System.exit(0);
	}

	@RequestMapping(value = "/muda/", method = RequestMethod.GET)
	@ResponseBody
	public String muda(String w) {
		// 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");

        jedis.hmset("test", new HashMap<String, String>() {{put("hello", "xiaoming");}});
        System.out.println(jedis.hmget("test", "hello"));
        // 查看服务是否运行
        System.out.println("" + jedis.ping());
        jedis.close();
		return"ok";
	}

	class Person{

	}
}
