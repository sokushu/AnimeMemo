package moe.neptunenoire;


import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import moe.neptunenoire.web.ThreadRun;

/**
 * 运行类
 * 配置
 */
@SpringBootApplication
public class MainRun {
	/**
	 * 图片文件存放路径
	 */
	public static final String filePath = "/home/BanGuMiKiRoKu/000/";
	
	/** 静态资源存放路径，例如文章等 "/home/BanGuMiKiRoKu/static/" */
	public static final String filePathStatic = "/home/BanGuMiKiRoKu/static/";
	
	/** 我的网站域名，用于防止跨站攻击 */
	public static String localhostname = "localhost";

	public enum FileType{
		IMG,
		txt,
		db,
	}
	
	/**
	 * 
	 * @return
	 */
	public static File getFilePath(FileType fileType) {
		return new File(new StringBuilder().append(filePath).append(File.separator).append(fileType.name()).toString());
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static File getFilePath(String path, FileType fileType) {
		return new File(new StringBuilder().append(filePath).append(File.separator).append(fileType.name()).
				append(File.separator).append(path).toString());
	}
	
	/**
	 * 
	 * @return
	 */
	public static File getPathStatic(FileType fileType) {
		return new File(new StringBuilder().append(filePathStatic).append(File.separator).append(fileType.name()).toString());
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static File getPathStatic(String path, FileType fileType) {
		return new File(new StringBuilder().append(filePathStatic).append(File.separator).
				append(fileType.name()).append(path).toString());
	}
	
	/**
	 * Main方法
	 */
	public static void main(String[] args) {

		/** 检查必要的文件夹是否存在 */
		for (FileType type : FileType.values()) {
			File file = getFilePath(type);
			if (!file.exists()) file.mkdirs();
			File staticFile = getPathStatic(type);
			if (!staticFile.exists()) staticFile.mkdirs();
		}
		
		/** 启动在后方运行的背景线程 */
		ThreadRun back = new ThreadRun();
		Thread backThread = new Thread(back, "backThread - 1");
		backThread.start();

		/** 启动Spring boot */
		SpringApplication.run(MainRun.class, args);
	}
}
