package moe.neptunenoire.web.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

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

	@RequestMapping(value = test, method = RequestMethod.GET)
	@ResponseBody
	public String adada(String id){
		FileReadAndLoad fileutil = new FileReadAndLoad("E:\\WorkFileSet\\partner\\codeSet\\Code\\JPIppanJouhou-InterBatch\\appl-sde-menkyo\\src\\main\\resources\\");
		try {
			Map<String, String> map = fileutil.ReadProperties("query.properties");
			return map.get(id);
		} catch (IOException e) {
			return "no";
		}

	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public void exit() {
		System.exit(0);
	}
}
