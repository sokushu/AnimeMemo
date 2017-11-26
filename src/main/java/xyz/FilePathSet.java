package xyz;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义的图片路径
 * @author pzr
 *
 */
@Configuration
public class FilePathSet extends WebMvcConfigurerAdapter  {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler中的是访问路径，可以修改为其他的字符串
        //addResourceLocations中的是实际路径
    	registry.addResourceHandler("/img/**").addResourceLocations("file:/home/miri/Work/000/");
    	registry.addResourceHandler("/file/**").addResourceLocations("classpath:/file/");
    	registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/admin/");
        super.addResourceHandlers(registry);
    } 
}