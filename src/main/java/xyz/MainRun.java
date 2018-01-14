package xyz;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import xyz.server.filter.Un_Sign_in;
/**
 * 运行类
 * 配置
 */
@SpringBootApplication
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
	//Windows10 调试
	public static String filePath = "E:\\Work\\MyProject\\000\\";
	/**
	 * 静态资源存放路径，例如文章等
	 */
	public static String filePath1 = "/home/BanGuMiKiRoKu/static/";


//==============================================
// 运行的Main方法
//  .   ____          _            __ _ _
//  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
//  ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
//  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
//   '  |____| .__|_| |_|_| |_\__, | / / / /
//  =========|_|==============|___/=/_/_/_/
//  :: Spring Boot ::        (v1.5.8.RELEASE)
//==============================================
	public static void main(String[] args) {
		SpringApplication.run(MainRun.class, args);
	}
	/**
	 * 设置错误页面
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

	   return (container -> {
	        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error.html");
	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error.html");
	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error.html");

	        container.addErrorPages(error401Page, error404Page, error500Page);
	   });
	}
	/**
	 * 设置session失效时间
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer1() {
		return new EmbeddedServletContainerCustomizer() {
			
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				// TODO Auto-generated method stub
				/**
				 * 设置失效时间为3600秒（1小时）
				 */
				container.setSessionTimeout(3600);
			}
		};
	}
	/**
	 * 设置端口号
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer2(){
		return new EmbeddedServletContainerCustomizer(){
		
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setPort(8080);
			}
		};
	}

	/**
	 * 自定义文件路径的设置
	 */
	@Configuration
	class FilePathSet extends WebMvcConfigurerAdapter{
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			//addResourceHandler中的是访问路径，可以修改为其他的字符串
        	//addResourceLocations中的是实际路径
			registry.addResourceHandler("/img/**").addResourceLocations("file:" + MainRun.filePath);
			registry.addResourceHandler("/static/**").addResourceLocations("file:" + MainRun.filePath1);
			registry.addResourceHandler("/file/**").addResourceLocations("classpath:/file/");
			registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/admin/");
			super.addResourceHandlers(registry);
		}
	}

	/**
	 * 过滤器的配置
	 */
	@Configuration
	class FilterSet extends WebMvcConfigurerAdapter{
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new Un_Sign_in())
			.addPathPatterns("/id/*/edit")
			.addPathPatterns("/bangumi/*/*")
			;
			super.addInterceptors(registry);
		}
	}
}
