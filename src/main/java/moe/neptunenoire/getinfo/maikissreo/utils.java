package moe.neptunenoire.getinfo.maikissreo;

/**
 * utils
 */
public class utils {
    public boolean isNull(String str){
        if (str == null) {
            return true;
        }else{
            return str.trim().length() > 0 ? false : true;
        }
    }
    
}