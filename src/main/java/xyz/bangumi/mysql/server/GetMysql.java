package xyz.bangumi.mysql.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GetMysql<K, V> {

    private static Map<String, Object> dataSave = new Hashtable<>();

    private static List<Map<String, Object>> dataSaveList = Collections.synchronizedList(new ArrayList<>());

    /**用来从数据库中得到各种数据 
     * @throws Error*/
    public GetMysql(String dataTypeString, String...strings) throws Error{
        /**获取有几个值输入 */
        int strlength = strings.length;
        if (dataTypeString.equals("anObject")) {
            
        }else if (dataTypeString.equals("anObject")) {
            
        }else if (dataTypeString.equals("anObject")) {
            
        }else{
            throw new Error("类型不匹配");
        }

    }

    public Map<String, Object> getDataSave(){
        return dataSave;
    }
    public List<Map<String, Object>> getDataSaveList(){
        return dataSaveList;
    }
}
