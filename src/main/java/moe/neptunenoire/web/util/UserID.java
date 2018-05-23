package moe.neptunenoire.web.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成UID
 * 使用的是hashcode进行生成
 */
public class UserID {
    //生成形式：7 固定形式 12 輸入字符串長度 13 hashcode長度 1 是否負值（負值） 154 防重複隨機數 125487541 hashcode
    private String username;

    private Long UserID;
    private String UserIDStr;
    private int hashcode;

    public static final int StringType = 0;
    public static final int LongType = 1;

    /**
     *
     * @param username
     */
    public UserID(String username){
        this.username = username;
        this.UserID = GetCode();
    }

    /**
     *
     * @param userid
     * @param type
     * @throws Exception
     */
    public UserID(Object userid, int type) throws Exception{
        if (type == StringType) {
            try {
                long userID = new Long((String)userid);
                this.UserID = userID;
                this.UserIDStr = (String)userid;
                String a = UserIDStr.substring(0, 1);
                if (a.equals("7") == false) {
                    throw new Exception("輸入的標準UserID");
                }
            } catch (Exception e) {
                throw new Exception("輸入的不是long類型");
            }
        }else if (type == LongType) {
            this.UserID = (long)userid;
            this.UserIDStr = Long.toString((long)userid);
            String a = UserIDStr.substring(0, 1);
            if (a.equals("7") == false) {
                throw new Exception("輸入的標準UserID");
            }
        }
    }

    /**
     * 生成UserID結果
     */
    private long GetCode(){
        int userL = username.length();
        int userHash = username.hashCode();
        int userHashL = Integer.toString(userHash).length();
        int randrom = 18 - 6 - userHashL;
        StringBuilder sb = new StringBuilder();
        sb.append("7");
        if (userL > 9) {
            sb.append(userL);
        }else{
            sb.append("0");
            sb.append(userL);
        }
        if (userHashL > 9) {
            sb.append(userHashL);
        }else{
            sb.append("0");
            sb.append(userHashL);
        }
        if (userHash > 0) {
            sb.append("0");
        }else{
            sb.append("1");
            userHash = Math.abs(userHash);
        }
        for (int i = 0; i < randrom; i++) {
            sb.append((int)(Math.random()*10));
        }
        sb.append(userHash);
        String userid = sb.toString();
        this.UserIDStr = userid;
        this.hashcode = userHash;
        return new Long(userid);
    }

    /**
     *
     */
    @Override
    public String toString() {
        return UserIDStr;
    }

    /**
     *
     * @return
     */
    public long GetlongCode(){
        return UserID;
    }

    /**
     *
     * @return
     */
    public int GetHashCode(){
        return hashcode;
    }

    /**
     * 返回一個集合，包含解析結果
     */
    public Map<String, Object> UnCode(){
        Map<String, Object>code = new HashMap<>();

        String b = UserIDStr.substring(3, 5);
        if (b.substring(0, 1).equals("0")) {
            b = b.substring(1);
        }
        int hashCodeL = new Integer(b);
        String c = UserIDStr.substring(1, 3);
        int userl = new Integer(c);
        String hashCode = UserIDStr.substring(UserIDStr.length() - (hashCodeL-1), UserIDStr.length());
        long theHashCode = new Long(hashCode);
        //用戶名長度
        code.put("UserNameL", userl);
        //hashcode
        code.put("HashCode", theHashCode);
        //hashcode的長度
        code.put("HashCodeL", hashCodeL);
        return code;
    }
}