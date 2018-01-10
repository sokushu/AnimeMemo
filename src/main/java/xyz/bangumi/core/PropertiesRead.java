package xyz.bangumi.core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 读取属性文件和写入属性文件，
 * 用于同步网页的显示。
 */
@Controller
@EnableAutoConfiguration
public class PropertiesRead {

    Properties prop = new Properties();
    public Model pageInfoModel(Model model){
        String key = "";
        try {
            /**对文件进行读取 */
            InputStream in = new BufferedInputStream(new FileInputStream("file"));
            extracted().load(in);
            Iterator<String> it = extracted().stringPropertyNames().iterator();
            while (it.hasNext()) {
                key = it.next();
                /**进行页面渲染 */
                model.addAttribute(key, extracted().getProperty(key));
            }
            in.close();
        } catch (IOException e) {System.err.println("发生错误 xyz.server.admin.Properties pageInfoModel");}
        return model;
    }

    @RequestMapping(value = "/admin/properties", method = RequestMethod.POST)
    /**写入文件 */
    public boolean Write(String keyString, String valueString){
        try {
            FileOutputStream fos = new FileOutputStream("file", true);
            extracted().setProperty(keyString, valueString);
            extracted().store(fos, "The New properties file");
            fos.close();
            return true;
		} catch (IOException e) {
            System.err.println("发生错误 xyz.server.admin.Properties Write");
            return false;
        }
    }

	private Properties extracted() {
		return prop;
	}
    
}