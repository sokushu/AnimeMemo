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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import moe.neptunenoire.MainRun;

/**
 * 文件的读取与写入
 */
public class FileReadAndLoad {

    /** 文件 */
    private File file = null;

    /**
     * 使用全局默认路径
     */
    public FileReadAndLoad(){
        this.file = new File(MainRun.filePath1);
        if (this.file.exists()) {
			this.file.mkdirs();
		}
    }

    /**
     * 使用自定义的路径
     * 需要输入要操作文件的路径
     */
    public FileReadAndLoad(String Path){
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
     * @param FileName
     * @return
     * @throws IOException
     */
    public String ReadTextForString(String FileName) throws IOException {
    	StringBuilder sb = new StringBuilder();
    	File readFile = new File(sb.append(file.getPath()).append(File.separator).append(FileName).toString());
    	if (!readFile.isFile()) {
			throw new FileNotFoundException("未找到文件" + file.getPath() + File.separator + FileName);
		}
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"))){
			return br.lines().collect(Collectors.joining());
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
     * @throws IOException
     */
    public void WriteHtml(String FileName, String html) throws IOException {
    	html = html.replaceAll("script", "div");

    	StringBuilder sb = new StringBuilder();
    	try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
    			sb.append(this.file.getPath()).append(File.separator).append(FileName).toString()), "UTF-8"))){
    		writer.write(html);
		} catch (IOException e) {
			throw e;
		}
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