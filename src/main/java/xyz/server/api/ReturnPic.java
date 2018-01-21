package xyz.server.api;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 从数据库中返回图片，可以方便的对图片进行删除，管理操作 */
@Controller
@EnableAutoConfiguration
public class ReturnPic {

    @RequestMapping(value = "/pic/{picid}", method = RequestMethod.GET)
    public String getPic(@PathVariable("picid")String picid){
        
        //数据库中查询图片后，返回图片路径
        return "";
    }
    
}