package moe.neptunenoire.web.phantom;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.util.StringCheck;


/**
 * 新番表
 */
@Component
public class BangumiList extends StringCheck {

    /** 数据库类 */
    private MaiKissReo maiReo;

    /** 得到用户的Session */
    @Autowired
    private HttpSession session;

    private String school;

    /** 所有的动画列表 */
    private Stream<Map<String, Object>> AllAnime;

    /** 数据初始化 */
    @Autowired
    public BangumiList(MaiKissReo maiReo){
        this.maiReo = maiReo;
        // 得到所有的动画数据，准备进行处理
        AllAnime = this.maiReo.Anime_FindAllAnime().stream();
    }
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

    /** 获取一部动画 */
    public Map<String, Object> getAnime(Integer animeID, String infoType) {
        List<Map<String, Object>> item = AllAnime.filter(var -> 
            ((Integer)(var.get("anime_id"))) == animeID
        ).collect(Collectors.toList());
        return item.size() > 0 ? item.get(0) : null;
    }
    
}