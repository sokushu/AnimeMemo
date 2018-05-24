package moe.neptunenoire.web.phantom;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moe.neptunenoire.web.database.DataSet;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.util.StringCheck;


/**
 * 新番表
 */
@Component
public class BangumiList extends StringCheck {

    /** 数据库类 */
	@Autowired
    private MaiKissReo maiReo;

    /** 得到用户的Session */
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

    /** 获取动画 */
    public List<Map<String, Object>> getAnime(Integer animeID, String infoType) {
        return DataSet.getAnimeData(var ->
        	((Integer)(var.get("anime_id"))) == animeID
        );
    }

}