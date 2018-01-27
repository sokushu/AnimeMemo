package xyz.server.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.bangumi.mysql.bean.Anime;
import xyz.bangumi.mysql.newsql.AnimeSql;

@Controller
@EnableAutoConfiguration
public class TheTest {
	
	@RequestMapping(value = "/test/the{name}", method = RequestMethod.GET)
	@ResponseBody
	public String adada(@PathVariable("name")String name){
		return "hello" + name;
	}
}
