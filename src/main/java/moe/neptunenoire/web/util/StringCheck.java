package moe.neptunenoire.web.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 对用户输入数据进行检查，防止进行攻击
 */
public class StringCheck {

    /**
     * 判断是否是数字
     * @param num
     * @return
     */
    public boolean isNumber(String num){
        try {
            @SuppressWarnings("unused")
            Integer a = new Integer(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @param num
     * @return
     */
    public String NumToString(int num){
        return String.valueOf(num);
    }

    /**
     *
     * @param num
     * @return
     */
    public String booleanToString(boolean num){
        return String.valueOf(num);
    }

    /**
     *
     * @param num
     * @return
     */
    public String doubleToString(double num){
        return String.valueOf(num);
    }

    /**
     * 字符串转数字
     * @param num
     * @return
     */
    public int StringToNum(String str) {
    	try {
    		return new Integer(str).intValue();
		} catch (Exception e) {
			return 0;
		}

    }

    /**
     * 检查是否有非法字符
     * 如果有的话，返回true
     */
    public boolean haveErrStr(String string){
        String[] ErrStr = {"!","@","#","$","%","^","&","*","(",")","{","}","\"","\\","[","]","_","=","+",".","|","/","`","<",">","?","'"};
        for (String var : ErrStr) {
            if (string.indexOf(var) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查字符串是否为空
     * <pre>
     * str = ""; 	//true
     * str = " "; 	//false
     * str = null;	//true
     * </pre>
     */
    public boolean isNull(String str){
        return str == null ? true : (str.trim()).length() > 0 ? false : true;
    }

    /**
     *
     * @param list
     * @return
     */
    public boolean isNull(List<?> list){
        return list == null ? true : list.size() > 0 ? false : true;
    }

    /**
     *
     * @param map
     * @return
     */
    public boolean isNull(Map<?,?> map){
        return map == null ? true : map.size() > 0 ? false : true;
    }

    /**
     *
     * @param long1
     * @return
     */
    public boolean isNull(Long long1){
        return long1 == null ? true : false;
    }

    /**
     * 检查时候是Null
     * @param inte
     * @return
     */
    public boolean isNull(Integer inte){
        return inte == null ? true : false;
    }

    /**
     * 获得当前时间
     * @return
     */
    public String getNowTime() {
    	return LocalDateTime.now().toString();
    }

}