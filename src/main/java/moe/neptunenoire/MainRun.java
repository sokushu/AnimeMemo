package moe.neptunenoire;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import moe.neptunenoire.web.ThreadRun;

/**
 * 运行类
 * 配置
 */
@SpringBootApplication
// @ServletComponentScan
public class MainRun {
	/**
	 * 图片文件存放路径
	 */

	//实际服务器
	//public static String filePath = "/home/BanGuMiKiRoKu/000/";

	//调试
	public static String filePath = "E:\\Work\\MyProject\\000\\";
	/** 静态资源存放路径，例如文章等 "/home/BanGuMiKiRoKu/static/" */
	public static String filePath1 = "/home/BanGuMiKiRoKu/static/";
	/** 我的网站域名，用于防止跨站攻击 */
	public static String localhostname = "localhost";

	/**
	 * Main方法
	 */
	public static void main(String[] args) {

		/** 在后方运行的背景线程 */
		ThreadRun back = new ThreadRun();
		Thread backThread = new Thread(back, "backThread - 1");
		backThread.start();

		/** 启动Spring boot */
		SpringApplication.run(MainRun.class, args);
	}
}
