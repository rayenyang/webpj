package com.rayenyang.webpj.config;

import com.rayenyang.webpj.web.WebPackageMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.LinkedList;
import java.util.List;

/**
 * SpringMvc相关配置
 * Created by rayenyang on 2017/1/4.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = WebPackageMarker.class)
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

    @Bean
    public HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypeList = new LinkedList<>();
        mediaTypeList.add(MediaType.parseMediaType("application/json;charset=UTF-8"));
        mediaTypeList.add(MediaType.parseMediaType("text/html"));
        mediaTypeList.add(MediaType.parseMediaType("text/plain;charset=UTF-8"));
        mediaTypeList.add(MediaType.parseMediaType("text/json;charset=UTF-8"));
        converter.setSupportedMediaTypes(mediaTypeList);
        return converter;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
