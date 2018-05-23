package moe.neptunenoire.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.web.ThreadRun;

/**
 * 这个类是用于在网页中进行系统管理的类
 * 
 * 例如主机的管理，程序的重新启动，升级，程序的微调整
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/setting")
public class SystemController {

    public static final String threadTime = "/threadtime/";
    public static final String exit = "/exit/";

    /**
     * 设置线程睡眠时间
     */
    @RequestMapping(value = threadTime, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> setThreadTime(Long time){
        ThreadRun.setTime(time);
        Map<String, String> map = new HashMap<>();
        Long timeItem = ThreadRun.getTime();
        if (timeItem == time) {
            map.put("isOK", "OK");
            map.put("time", String.valueOf(timeItem));
        }else{
            map.put("isOK", "NO");
            map.put("time", "0");
        }
        return map;
    }

    /**
     * 停掉当前的程序
     */
    @RequestMapping(value = exit, method = RequestMethod.POST)
    @ResponseBody
    public String shotDown(HttpSession session){
        System.exit(0);
        return "byebye";
    }
    
}