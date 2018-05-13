package moe.neptunenoire.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import org.springframework.ui.Model;
/**
 * 读取属性文件和写入属性文件，
 * 用于同步网页的显示。
 */
public class PropertiesRead {

    Properties prop = new Properties();
    public Model pageInfoModel(Model model){
        String key = "";
        try {
            /**对文件进行读取 */
            InputStream in = new BufferedInputStream(new FileInputStream("file"));
            prop.load(in);
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                key = it.next();
                /**进行页面渲染 */
                model.addAttribute(key, prop.getProperty(key));
            }
            in.close();
        } catch (IOException e) {System.err.println("发生错误 xyz.server.admin.Properties pageInfoModel");}
        return model;
    }

    /**写入文件 */
    public boolean Write(String keyString, String valueString){
        try {
            FileOutputStream fos = new FileOutputStream("file", true);
            prop.setProperty(keyString, valueString);
            prop.store(fos, "The New properties file");
            fos.close();
            return true;
		} catch (IOException e) {
            System.err.println("发生错误 xyz.server.admin.Properties Write");
            return false;
        }
    }  
}