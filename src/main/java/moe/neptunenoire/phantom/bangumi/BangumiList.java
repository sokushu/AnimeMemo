package moe.neptunenoire.phantom.bangumi;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import moe.neptunenoire.mysql.dao.MaiKissReo;
import moe.neptunenoire.util.StringCheck;

/**
 * 新番表
 */
public class BangumiList extends StringCheck {

    @Autowired
    private MaiKissReo maiReo;

    @Autowired
    private HttpSession session;

    /**
     * 处理新番表数据
     * 
     * 新番表就是将所有的新番集合起来，
     * 进度，各种信息的集中管理
     * @return 返回的是处理好的新番表数据
     */
    public List<String> bangumiPrco(){

        return null;
    }
    
}