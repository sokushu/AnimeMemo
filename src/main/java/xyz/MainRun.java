package xyz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
/**
 * 核心运行类
 */
@SpringBootApplication
public class MainRun {

	public static void main(String[] args) {
		SpringApplication.run(MainRun.class, args);
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

	   return (container -> {
	        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error.html");
	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error.html");
	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error.html");

	        container.addErrorPages(error401Page, error404Page, error500Page);
	   });
	}
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer1() {
		return new EmbeddedServletContainerCustomizer() {
			
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				// TODO Auto-generated method stub
				container.setSessionTimeout(3600);
			}
		};
	}
}
