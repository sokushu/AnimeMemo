package moe.neptunenoire.web.util;

import java.util.HashMap;
import java.util.Map;

public class UserID {
	//生成形式：7 固定形式 12 輸入字符串長度 13 hashcode長度 1 是否負值（負值） 154 防重複隨機數 125487541 hashcode

	private String username;

    private Long UserID;

    private String UserIDStr;

    /**
     *
     * @param username
     */
    public UserID(String username){
        this.username = username;
		this.UserIDStr = code(username);
		this.UserID = Long.parseLong(UserIDStr);
    }

    /**
     * 生成UserID結果
     */
    private String code(String username){

		int hashcode = username.hashCode();
		int sum = username.chars().sum() + Math.abs(hashcode);

		char[] ab = String.valueOf(sum).toCharArray();
		char[] ko = new char[16];
		ko[0] = '7';
		int len = username.length();
		char[] strLen = String.valueOf(username.length()).toCharArray();
		if (len > 9) {
			ko[1] = strLen[0];
			ko[2] = strLen[1];
		}else {
			ko[1] = '0';
			ko[2] = strLen[0];
		}
		if (hashcode > 0) {
			ko[3] = '0';
		}else {
			ko[3] = '1';
		}
		for (int i = 4; i < 16 - ab.length; i++) {
			ko[i] = '0';
		}
		int j = 0;
		for (int i = 16 - ab.length; i < 16; i++) {
			if (j == ab.length) {
				break;
			}
			ko[i] = ab[j];
			j ++;
		}
		return String.valueOf(ko);
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

    public String getUserName() {
    	return username;
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
