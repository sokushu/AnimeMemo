package xyz;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import xyz.bangumi.filter.UN_SIGN_IN;
/**
 * 过滤器的配置
 */
@Configuration
public class FilterSetting extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new UN_SIGN_IN())
			.addPathPatterns("/id/*/edit")
			.addPathPatterns("/bangumi/*/*")
			;
//		registry.addInterceptor(new Sign_in()).addPathPatterns("/sign_in");
		super.addInterceptors(registry);
	}
}
