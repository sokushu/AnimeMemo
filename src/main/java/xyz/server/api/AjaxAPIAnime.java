package xyz.server.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.bangumi.mysql.dao.AnimeDao;

/**
 * Created by xchunzhao on 02/05/2017.
 */
@RestController
@RequestMapping("/api/anime")
public class AjaxAPIAnime {

    @Autowired
    private AnimeDao anime;
    
    /**
     * 返回单个动画的数据 BY ID
     * @param animeid
     * @return
     */
    @RequestMapping(value = "/id/{meth}", method = RequestMethod.GET)
    public Map<String, Object> findOneAnime(@PathVariable("meth") String meth, Model model) {
        return anime.findByAnimeID(meth);
    }
    
    /**
     * 根据名字返回
     * 因为浏览器编码不同，不建议使用
     * @param meth
     * @return
     */
    @RequestMapping(value = "/name/{meth}", method = RequestMethod.GET)
    public List<Map<String, Object>> findOneAnime1(@PathVariable("meth") String meth) {
		return anime.findBYAnimeName(meth);
    }
    
    /**
     * 获取全部的动画数据
     * @return
     */
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public List<Map<String, Object>> returnAllAnime(){
    	return anime.returnAllAnime();
    }
    
    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
    public List<Map<String, Object>> search(@PathVariable("name")String name){
    	return anime.searchAnime(name);
    }
    
    /**
     * 获取首页的最新添加的8个动画
     */
    @RequestMapping(value = "/indexanime", method = RequestMethod.GET)
    public List<Map<String, Object>> returnIndexAnime(){
    	return anime.findByData();
    }
}