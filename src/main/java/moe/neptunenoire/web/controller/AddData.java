package moe.neptunenoire.web.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import moe.neptunenoire.web.table.Anime;
import moe.neptunenoire.web.table.Music;

@Controller
@EnableAutoConfiguration
public class AddData {

	@RequestMapping(value = "/data/bangumi", method = RequestMethod.POST)
	public String addBangumi(Anime anime) {
		return "";
	}

	@RequestMapping(value = "/data/bangumi", method = RequestMethod.GET)
	public String showPage(){
		return "add_data";
	}

	@RequestMapping(value = "/data/music", method = RequestMethod.POST)
	public String addMusic(Music music) {
		return "";
	}

}
