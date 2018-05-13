package moe.neptunenoire.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import moe.MainRun;

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
    }

    /** 
     * 使用自定义的路径
     * 需要输入要操作文件的路径
     */
    public FileReadAndLoad(String Path){
        if (Path == null) {
            this.file = new File(MainRun.filePath1);
        }
        File file = new File(Path);
        //判断是否存在文件夹
        if (file.exists() == false) {
            file.mkdir();
        }
        this.file = file;
    }

    /** 读取图片，视频，音频等其他内容 */
    public void ReadFileRich(String Path, String FileName){
        
    }


    /**
     * 一行一行的读取文本
     * 输入文件名
     * @param FileName 文件名
     * @throws IOException
     */
    public List<String> ReadTextByLine(String FileName) throws IOException{
        File readFile = new File(file.getPath() + FileName);
        if (!readFile.exists()) {
            throw new FileNotFoundException("未找到文件" + file.getPath() + FileName);
        }
        try (BufferedReader buffedread = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"))){
            return buffedread.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 将文本写入文件当中
     * @throws Exception
     */
    public void writeText(String FileName, String[] text) throws Exception {
        WriteText(FileName, Stream.of(text).collect(Collectors.toList()));
    }

    /**
     * 将文本写入文件当中
     */
    public void WriteText(String FileName, List<String> text) throws Exception{
        File writeFile = new File(file.getPath() + FileName);
        if (!writeFile.exists()) {
            writeFile.mkdirs();
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFile), "UTF-8"))){
            bufferedWriter.write(
                String.join(System.lineSeparator(), text)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}