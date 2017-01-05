package com.rayenyang.webpj.config;

import com.rayenyang.webpj.BasePackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring配置
 * Created by rayenyang on 2017/1/4.
 */
@Configuration
@ComponentScan(basePackageClasses = BasePackageMarker.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class))
public class ApplicationConfig {
}
