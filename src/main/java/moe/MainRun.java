package moe;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 运行类
 * 配置
 */
@SpringBootApplication
// @ServletComponentScan
public class MainRun {

	/**
	 * map集合
	 * 用于储存数据库信息
	 */
	public static Map<String, Object> MySQLReaderData = new ConcurrentHashMap<>();
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
		SpringApplication.run(MainRun.class, args);
	}
	/**
	 * 设置错误页面
	 */
	@Bean
	public ErrorPageRegistrar errorPageRegistrar(){
		return new MyErrorPageRegistrar();
	}
	private static class MyErrorPageRegistrar implements ErrorPageRegistrar{
		@Override
		public void registerErrorPages(ErrorPageRegistry registry) {
			registry.addErrorPages(
				new ErrorPage(HttpStatus.BAD_REQUEST, "/error.html"),
				new ErrorPage(HttpStatus.NOT_FOUND, "/error.html")
				);
		}
	}

	/**
	 * 拦截器的相关配置
	 */
	@Configuration
	public static class MyInterceptor extends WebMvcConfigurationSupport {

		// /**
		//  * 拦截器的相关配置
		//  */
		// @Override
		// protected void addInterceptors(InterceptorRegistry registry) {
		// 	// 添加权限拦截器
		// 	registry.addInterceptor(new UserYuri()).addPathPatterns("/**");
		// 	// 添加登陆拦截器
		// 	registry.addInterceptor(new Un_Sign_in()).addPathPatterns("/**");
		// 	super.addInterceptors(registry);
		// }

		/**
		 * 静态资源映射
		 */
		@Override
		protected void addResourceHandlers(ResourceHandlerRegistry registry) {
			super.addResourceHandlers(registry);
			registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		}

		
	}
}
