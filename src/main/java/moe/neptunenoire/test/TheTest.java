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

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.mysql.dao.MaiKissReo;
import moe.neptunenoire.util.MD5Coding;

@Controller
@EnableAutoConfiguration
public class TheTest {
	public static final String test = "/test/";

	public String id = null;

	public String time = String.valueOf(Calendar.getInstance().getTimeInMillis());

	public MaiKissReo session;

	@Autowired
	public TheTest(MaiKissReo session){
		this.session = session;
		session.Anime_FindAllAnime();
	}

	@RequestMapping(value = test, method = RequestMethod.GET)
	@ResponseBody
	public String adada(String id){
		List<String> a = new ArrayList<>();

		a.add("e");
		return a.get(2);
		
	}
	
	@RequestMapping(value = test + "add", method = RequestMethod.GET)
	@ResponseBody
	public String name() {
		session.setAttribute("OK", "let's go!!");
		return "OK";
	}
}
