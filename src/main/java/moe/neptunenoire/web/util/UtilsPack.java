package moe.neptunenoire.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import moe.neptunenoire.MainRun;

/**
 * 对用户输入数据进行检查，防止进行攻击
 */
public class UtilsPack {

	public StringUtil getStringUtil() {
		return new StringUtil();
	}

	public class StringUtil{

		private StringUtil() {}
		/**
	     * 判断是否是数字
	     * @param num
	     * @return
	     */
	    public boolean isNumber(String num){
	    	try {
	    		Integer.parseInt(num);
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
	        return !(str != null && str.length() > 0);
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
	        return long1 == null;
	    }

	    /**
	     * 检查时候是Null
	     * @param inte
	     * @return
	     */
	    public boolean isNull(Integer inte){
	        return inte == null;
	    }

	    /**
	     * 获得当前时间
	     * @return
	     */
	    public String getNowTime() {
	    	return LocalDateTime.now().toString();
	    }
	}

	public FileReadAndLoad getFileReadAndLoad() {
		return new FileReadAndLoad();
	}

	public FileReadAndLoad getFileReadAndLoad(String Path) {
		return new FileReadAndLoad(Path);
	}

	/**
	 * 文件的读取与写入
	 */
	public class FileReadAndLoad {

	    /** 文件 */
	    private File file = null;

	    /**
	     * 使用全局默认路径
	     */
	    private FileReadAndLoad(){
	        this.file = new File(MainRun.filePath1);
	        if (this.file.exists()) {
				this.file.mkdirs();
			}
	    }

	    /**
	     * 使用自定义的路径
	     * 需要输入要操作文件的路径
	     */
	    private FileReadAndLoad(String Path){
	        if (Path == null) {
	            this.file = new File(MainRun.filePath1);
	            if (this.file.exists()) {
	    			this.file.mkdirs();
	    		}
	        }
	        File file = new File(Path);
	        //判断是否存在文件夹
	        if (file.exists() == false) {
	            file.mkdirs();
	        }
	        this.file = file;
	    }

	    /**
	     *
	     * @param Path
	     */
	    public void changePath(String Path) {
	    	file = new File(Path);
	    }

	    /**
	     *
	     * @return
	     */
	    public String getPath() {
	    	return file.getPath();
	    }

	    /**
	     * 读取图片，视频，音频等其他内容
	     * @param FileName
	     * @return
	     */
	    public byte[] ReadToByte(String FileName){
	    	return null;
	    }


	    /**
	     * 一行一行的读取文本
	     * 输入文件名
	     * @param FileName 文件名
	     * @throws IOException
	     */
	    public List<String> ReadTextByLine(String FileName) throws IOException{
	        File readFile = new File(file.getPath() + File.separator + FileName);
	        if (!readFile.isFile()) {
	            throw new FileNotFoundException("未找到文件" + file.getPath() + File.separator + FileName);
	        }
	        try (BufferedReader buffedread = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"))){
	            return buffedread.lines().collect(Collectors.toList());
	        } catch (Exception e) {
	            throw e;
	        }
	    }

	    /**
	     * 将文本写入文件当中
	     * @throws Exception
	     */
	    public void WriteText(String FileName, String[] text) throws Exception {
	        WriteText(FileName, Arrays.asList(text));
	    }

	    /**
	     * 将文本写入文件当中
	     */
	    public void WriteText(String FileName, List<String> text) throws Exception{
	        File writeFile = new File(file.getPath() + File.separator + FileName);
	        if (!writeFile.exists()) {
	            writeFile.createNewFile();
	        }
	        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFile), "UTF-8"))){
	            bufferedWriter.write(
	                String.join(System.lineSeparator(), text)
	            );
	        } catch (Exception e) {
	            throw e;
	        }
	    }

	    /**
	     *
	     * @param FileName
	     * @param split
	     * @return
	     * @throws Exception
	     */
	    public Map<String, String> ReadTextAndSplit(String FileName, String split) throws Exception {
	    	File readFile = new File(file.getPath() + File.separator + FileName);
	    	if (!readFile.isFile()) {
				throw new FileNotFoundException("未找到文件" + file.getPath() + File.separator + FileName);
			}
	    	try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"))){
				return br.lines().filter(var->filterEMP(var)).map(var->var.split(split)).collect(Collectors.toMap(var->var[0], var->var[1]));
			} catch (Exception e) {
				throw e;
			}
	    }

	    /**
	     *
	     * @param FileName
	     * @param text
	     * @throws IOException
	     */
	    public void WriteTextForMap(String FileName, Map<String, String> text) throws IOException {
	    	File writeFile = new File(file.getPath() + File.separator + FileName);
	        if (!writeFile.exists()) {
	            writeFile.createNewFile();
	        }
	        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFile), "UTF-8"))){
	            for (String var : text.keySet()) {
					bufferedWriter.write(var+"="+text.get(var)+System.lineSeparator());
				}
	        } catch (Exception e) {
	            throw e;
	        }
	    }

	    /**
	     *
	     * @return
	     */
	    public File[] listFiles() {
	    	return file.listFiles();
	    }

	    /**
	     *
	     * @param str
	     * @return
	     */
	    private boolean filterEMP(String str) {
	    	return str != null && str.length() >= 3;
	    }

	    /**
	     *
	     * @return
	     * @throws IOException
	     */
	    public Map<String, String> ReadProperties(String FileName) throws IOException{
	    	Properties prop = new Properties();
	    	Map<String, String> map = new HashMap<>();

	    	/**对文件进行读取 */
	    	try (InputStream in = new BufferedInputStream(new FileInputStream(this.file.getPath() + File.separator +  FileName))){

	            prop.load(new InputStreamReader(in, "UTF-8"));

	            prop.stringPropertyNames().forEach(var -> {
	            	map.put(var, prop.getProperty(var, "null"));
	            });

	            return map;
	        } catch (IOException e) {
	        	throw e;
	        }
	    }

	    /**
	     *
	     * @param FileName
	     * @param text
	     * @throws IOException
	     */
	    public void WriteProperties(String FileName, Map<String, String> text) throws IOException {
	    	Properties prop = new Properties();
	    	try {
	            FileOutputStream fos = new FileOutputStream("D:\\Test\\proper", true);
	            for (String var : text.keySet()) {
	            	prop.setProperty(var, text.get(var));
				}

	            prop.store(new OutputStreamWriter(fos, "UTF-8"), "The New properties file");
	            fos.close();
			} catch (IOException e) {
	            throw e;
	        }
	    }

	    /**
	     *
	     * @param FileName
	     * @param html
	     */
	    public void HtmlWrite(String FileName, String html) {
	    	html = html.replaceAll("", "");
	    }

	    /**
	     *
	     * @param FileName
	     * @return
	     */
	    public String HtmlReader(String FileName) {
	    	return null;
	    }

	    public void ReadXML(String FileName) {

	    }

	    public void WriteXML() {

	    }
	}

	/**
	 * MD5Coding
	 */
	public static class MD5Coding {

	    /**
	     * 对密码进行加密
	     * @param username
	     * @param password
	     * @param uid
	     * @return
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

	public UserID getUserID(String username) {
		return new UserID(username);
	}

	public enum Type{
    	StringType,
    	LongType
    }
}

/**
 * 生成UID
 * 使用的是hashcode进行生成
 */
class UserID extends UtilsPack {
    //生成形式：7 固定形式 12 輸入字符串長度 13 hashcode長度 1 是否負值（負值） 154 防重複隨機數 125487541 hashcode

	private String username;

    private Long UserID;

    private String UserIDStr;



    /**
     *
     * @param username
     */
    UserID(String username){
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