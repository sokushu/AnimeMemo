package xyz.server.home;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 将统计各种信息，动画信息等
 */
@Controller
@EnableAutoConfiguration
public class DataCPU {

    @RequestMapping(value = "/id/{url}/statistics/anime", method = RequestMethod.GET)
    /**拿到动画的各种统计信息 */
    public String getAnimeData(@PathVariable("url")String url) {
        /**对数据库进行检索计算 */
        return "";
    }

    @RequestMapping(value = "/id/{url}/statistics/jishi", method = RequestMethod.GET)
    /**记事的统计信息 */
    public String getJishiData(@PathVariable("url")String url){
        return "";
    }
}