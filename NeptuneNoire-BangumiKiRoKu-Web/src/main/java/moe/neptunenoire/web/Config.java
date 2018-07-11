package moe.neptunenoire.web;

import java.util.Map;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import moe.neptunenoire.web.database.RedisObjectSerializer;

/**
 * 配置设置类
 */
@Component
public class Config {

	/**
	 *
	 * @author M
	 *
	 */
	@Configuration
	public static class MyInterceptor extends WebMvcConfigurationSupport {

//		 /**
//		  * 拦截器的相关配置
//		  */
//		 @Override
//		 protected void addInterceptors(InterceptorRegistry registry) {
//		 	// 添加权限拦截器
//		 	registry.addInterceptor(new UserYuri()).addPathPatterns("/**");
//		 	// 添加登陆拦截器
//		 	registry.addInterceptor(new Un_Sign_in()).addPathPatterns("/**");
//		 	super.addInterceptors(registry);
//		 }

		/**
		 * 静态资源映射
		 */
		@Override
		protected void addResourceHandlers(ResourceHandlerRegistry registry) {
			super.addResourceHandlers(registry);
			registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		}


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
	 *
	 * @author M
	 *
	 */
	@Configuration
	public class WebSocketConfig {
	    @Bean
	    public ServerEndpointExporter serverEndpointExporter() {
	        return new ServerEndpointExporter();
	    }

	}

	@Configuration
	public class RedisConfig extends CachingConfigurerSupport {


	    @Bean
	    public RedisTemplate<String, Map<String, Object>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

	        RedisTemplate<String, Map<String, Object>> template = new RedisTemplate<String, Map<String, Object>>();
	        template.setConnectionFactory(redisConnectionFactory);
	        template.setKeySerializer(new StringRedisSerializer());
	        template.setValueSerializer(new RedisObjectSerializer());
	        return template;
	    }

	}
}
