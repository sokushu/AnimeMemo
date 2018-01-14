package xyz.server.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.bangumi.mysql.bean.Anime;
import xyz.bangumi.mysql.newsql.AnimeSql;

@Controller
@EnableAutoConfiguration
public class TheTest {
	
//	@RequestMapping(value = "/test/mysql", method = RequestMethod.GET)
//	@ResponseBody
//    public List<Map<String, Object>> likeName(@RequestParam String name){
// 	    PageHelper.startPage(1, 2);
//        return anime.returnAllAnime();
//    }
	
// 	@RequestMapping(value = "/test/ru", method = RequestMethod.PUT)
// 	@ResponseBody
// 	public String tes(String word) {
// 		return "Hello World" + word;
// 	}
	
// 	@DeleteMapping(value = "/test/ru")
// 	@ResponseBody
// 	public String tedd(String word) {
// 		return "HELLO WORLD" +" " +word;
// 	}
	
	@RequestMapping(value = "/test/", method = RequestMethod.GET)
	@ResponseBody
	public String adada(){
		Anime a = new Anime();
		a.setAnime_name("anime_name");
		a.setAnime_class("anime_class");
		a.setAnime_number(10);
		a.setAnime_info_daoyan(" ");
		a.setAnime_info_edsonger(" ");
		return new AnimeSql().insert(a);
	}
}
