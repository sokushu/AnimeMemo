package xyz.bangumi.mysql.server;

import org.springframework.beans.factory.annotation.Autowired;

import xyz.MainRun;
import xyz.bangumi.mysql.dao.AnimeDao;
import xyz.bangumi.mysql.dao.CommentsDao;
import xyz.bangumi.mysql.dao.MessageDao;
import xyz.bangumi.mysql.dao.SELECT;
import xyz.bangumi.mysql.dao.TagDao;
import xyz.bangumi.mysql.dao.Tag_AnimeDao;
import xyz.bangumi.mysql.dao.UserDao;
import xyz.bangumi.mysql.dao.User_AnimeDao;

/**
 * 主要的数据库读取操作的类
 */

public class Server {

    @Autowired
    private AnimeDao animeDao;
    @Autowired
    private CommentsDao commentsDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private SELECT select;
    @Autowired
    private Tag_AnimeDao tag_AnimeDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private User_AnimeDao user_AnimeDao;
    @Autowired
    private UserDao userDao;

    /**
     * 信息的添加
     */
    public void addDataToMap(String key, Object info){
        MainRun.MySQLReaderData.put(key, info);
    }
    /**
     * 信息的删除
     */
    public void removeDataToMap(String key){
        MainRun.MySQLReaderData.remove(key);
    }
    /**
     * 信息的读取
     * 有可能是LIST<Map<String, Object>>
     * 有可能是Map<String, Object>
     */
    public Object readData(String key){
        return MainRun.MySQLReaderData.get(key);
    }
    
}
