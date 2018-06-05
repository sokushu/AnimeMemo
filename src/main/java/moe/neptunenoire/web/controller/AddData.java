package moe.neptunenoire.web.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;

import moe.neptunenoire.web.table.Anime;
import moe.neptunenoire.web.table.Music;

@Controller
@EnableAutoConfiguration
public class AddData {

	public String addBangumi(Anime anime) {
		return "";
	}

	public String addMusic(Music music) {
		return "";
	}

}
