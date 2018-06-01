package moe.neptunenoire.web.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.web.util.FileReadAndLoad;


@Controller
@EnableAutoConfiguration
public class TheTest {
	public static final String test = "/test/";

	public String id = null;

	public String time = String.valueOf(Calendar.getInstance().getTimeInMillis());

	FileReadAndLoad fileutil = new FileReadAndLoad("D:\\Test");

	DB db = DBMaker.fileDB("").closeOnJvmShutdown().make();


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
		BTreeMap<String, Object> da = db.treeMap("da", Serializer.STRING, Serializer.ILLEGAL_ACCESS).createOrOpen();
		return "ok";
	}
}
