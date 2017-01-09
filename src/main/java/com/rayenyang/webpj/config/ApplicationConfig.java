package com.rayenyang.webpj.config;

import com.google.gson.Gson;
import com.rayenyang.webpj.BasePackageMarker;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring配置
 * Created by rayenyang on 2017/1/4.
 */
@Configuration
@ComponentScan(basePackageClasses = BasePackageMarker.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class))
@PropertySource(value = "classpath:webpj.properties",ignoreResourceNotFound = true,
        encoding = "UTF-8")
public class ApplicationConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Gson commonGson(){
        return new Gson();
    }
}
