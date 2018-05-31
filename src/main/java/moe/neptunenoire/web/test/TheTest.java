package moe.neptunenoire.web.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.web.util.FileReadAndLoad;
import moe.neptunenoire.web.util.MuDa;


@Controller
@EnableAutoConfiguration
public class TheTest {
	public static final String test = "/test/";

	public String id = null;

	public String time = String.valueOf(Calendar.getInstance().getTimeInMillis());

	FileReadAndLoad fileutil = new FileReadAndLoad("E:\\WorkFileSet\\partner\\codeSet\\Code\\JPIppanJouhou-InterBatch\\appl-sde-menkyo\\src\\main\\resources\\");

	Map<String, String> map;

	List<String> list;

	public TheTest() {
		 try {
			map = fileutil.ReadProperties("BfCustomMsg.properties");
			list = fileutil.ReadTextByLine("BfCustomMsg.properties");
		} catch (IOException e) {}
	}

	@RequestMapping(value = test, method = RequestMethod.GET)
	@ResponseBody
	public List<String> adada(String id){
		return list;
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public void exit() {
		System.exit(0);
	}

	@RequestMapping(value = "/muda/", method = RequestMethod.GET)
	@ResponseBody
	public String muda(String w) {
		return MuDa.charPlus(w);
	}
}
