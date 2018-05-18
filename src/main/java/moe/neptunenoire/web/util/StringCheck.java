package moe.neptunenoire.web.util;

import java.util.List;
import java.util.Map;

/**
 * 对用户输入数据进行检查，防止进行攻击
 */
public class StringCheck {
    
    /** 判断是否是数字 */
    public boolean isNumber(String num){
        try {
            @SuppressWarnings("unused")
            Integer a = new Integer(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /** 字符串转数字 */
    public int StringToNum(String num){
        if (isNull(num) == false) {
            try {
                Integer a = new Integer(num);
                return a;
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }
    public String NumToString(int num){
        return String.valueOf(num);
    }
    public String booleanToString(boolean num){
        return String.valueOf(num);
    }
    public String doubleToString(double num){
        return String.valueOf(num);
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
     */
    public static boolean isNull(String str){
        if (str == null) {
            return true;
        }else{
            return (str.trim()).length() > 0 ? false : true;
        }
    }
    public static boolean isNull(List<?> list){
        if (list == null) {
            return true;
        }else{
            return list.size() > 0 ? false : true;
        }
    }
    public static boolean isNull(Map<?,?> map){
        if (map == null) {
            return true;
        }else{
            return map.size() > 0 ? false : true;
        }
    }
    public static boolean isNull(Long long1){
        if (long1 == null) {
            return true;
        }else{
            return false; 
        }
    }
    public static boolean isNull(Integer inte){
        if (inte == null) {
            return true;
        }else{
            return false;
        }
    }
}