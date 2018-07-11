package moe.neptunenoire.Process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MakeFile {

	private static Map<OutType, String> linkDriver = new HashMap<OutType, String>(){
		private static final long serialVersionUID = 3216904991311995025L;
		{
			put(OutType.Html, "<br/>");
			put(OutType.newLine, System.lineSeparator());
		}
	};
	
	/**
	 * 创建文件目录
	 * 如果文件存在，则返回False
	 * @param filePath
	 * @return
	 */
	public boolean makePath(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return file.mkdirs();
		}
		return false;
	}
	
	/**
	 * 将文件读取为字符串形式
	 * @param filePath
	 * @param outType
	 * @return
	 */
	public String readtext(String filePath, OutType outType) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))){
			return br.lines().collect(Collectors.joining(linkDriver.get(outType)));
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public enum OutType{
		Html, newLine,
	}
	
}
