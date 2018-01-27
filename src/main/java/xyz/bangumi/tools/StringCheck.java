package xyz.bangumi.tools;

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
            //TODO: handle exception
            return false;
        }
    }
    /** 字符串转数字 */
    public int StringToNum(String num){
        try {
            Integer a = new Integer(num);
            return a;
        } catch (Exception e) {
            //TODO: handle exception
            return 0;
        }
    }
    /** 检查是否有非法字符 */
    public boolean haveErrStr(String string){
        String[] ErrStr = {"!","@","#","$","%","^","&","*","(",")","{","}","\"","\\","[","]","_","=","+",".","|","/","`","<",">","?","'"};
        for (String var : ErrStr) {
            if (string.indexOf(var) > -1) {
                return true;
            }
        }
        return false;
    }
}