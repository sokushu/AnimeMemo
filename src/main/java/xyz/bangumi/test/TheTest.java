package xyz.bangumi.test;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.bangumi.mysql.dao.SELECT;

@Controller
@EnableAutoConfiguration
public class TheTest {

	@Autowired
	private SELECT select;
	
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
	
	@RequestMapping(value = "/test/ru", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> tesd(String word) {
		return select.findWatchingAnimeInfo("29");
	}
}
