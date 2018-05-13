package moe.neptunenoire.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * MD5Coding
 */
public class MD5Coding {

    /**
     * 对密码进行加密
     */

    public static String coding(String username, String password, long uid){

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            String md5Pass = username + password + uid + "MK2";

            byte[] md5A = md5.digest(md5Pass.getBytes());

            byte[] sha1B = sha1.digest(md5A);
            return Base64.getEncoder().encodeToString(sha1B);
		} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
		}
        
    }
    
}