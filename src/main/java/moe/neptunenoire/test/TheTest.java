package moe.neptunenoire.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.util.MD5Coding;

@Controller
@EnableAutoConfiguration
public class TheTest {
	public static final String test = "/test/";

	public String id = null;

	public String time = String.valueOf(Calendar.getInstance().getTimeInMillis());

	@Autowired
	public HttpSession session;

	List<Map<String, String>> list = new ArrayList<Map<String, String>>(){
		private static final long serialVersionUID = 1L;
		{
			for (int i = 0; i < 50000; i++) {
				Map<String, String> map = new HashMap<>();
				map.put("uid", String.valueOf(i));
				map.put("username", "I'm "+i);
				map.put("key", "value" + i);
				map.put("name", "name " + i);
				map.put("from", "name " + i);
				map.put("music", "name " + i);
				map.put("info", "name " + i);
				map.put("url", "name " + i);
				map.put("num", "name " + i);
				map.put("kd", "name " + i);
				map.put("ff", "name " + i);
				map.put("ffd", "name " + i);
				map.put("bn", "namemap.put(\"name\", \"name \" + i); " + i);
				add(map);
			}
		}
	};
	public TheTest(){
		
	}
	@RequestMapping(value = test, method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> adada(String id){
		
		return list.stream().filter(var->var.get("uid").equals(id)).collect(Collectors.toList());
	}
	
	@RequestMapping(value = test + "add", method = RequestMethod.GET)
	@ResponseBody
	public String name() {
		session.setAttribute("OK", "let's go!!");
		return "OK";
	}
}
