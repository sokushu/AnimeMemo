package moe.neptunenoire.web.test;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
public class TheTest {
	public static final String test = "/test/";

	public String id = null;

	public String time = String.valueOf(Calendar.getInstance().getTimeInMillis());

	@RequestMapping(value = test, method = RequestMethod.GET)
	@ResponseBody
	public String adada(String id){
		String a = "a a a a a cc";
		int bbb = 0;

		Matcher matcher = Pattern.compile("a").matcher(a);
		while (matcher.find()) {
			a = a.replaceAll("a", "b");
			bbb ++;
		}
		return a+ " ===" +bbb;
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public void exit() {
		System.exit(0);
	}
}
