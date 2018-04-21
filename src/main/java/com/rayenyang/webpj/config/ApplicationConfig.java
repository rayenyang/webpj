package com.rayenyang.webpj.config;

import com.google.gson.Gson;
import com.rayenyang.webpj.BasePackageMarker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.Executor;

/**
 * Spring配置
 * Created by rayenyang on 2017/1/4.
 */
@Configuration
@ComponentScan(basePackageClasses = BasePackageMarker.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class))
@PropertySource(value = "classpath:webpj.properties", ignoreResourceNotFound = true,
        encoding = "UTF-8")
@EnableAspectJAutoProxy
@EnableAsync(proxyTargetClass = true)
@EnableScheduling()
public class ApplicationConfig implements AsyncConfigurer, SchedulingConfigurer {
    
    private static final Log log = LogFactory.getLog(ApplicationConfig.class);
    @Autowired
    private Environment env;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public Gson commonGson() {
        return new Gson();
    }
    
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        return scheduler;
    }
    
    @Override
    public Executor getAsyncExecutor() {
        return this.threadPoolTaskScheduler();
    }
    
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error(throwable.getStackTrace());
        };
    }
    
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(this.threadPoolTaskScheduler());
    }
    
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }
    
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(this.localValidatorFactoryBean());
        return processor;
    }
}
