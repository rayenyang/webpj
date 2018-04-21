package com.rayenyang.webpj.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * description:
 * Created by rayenyang on 2017/8/4.
 */
@Profile("dev")
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(requestHandler -> requestHandler != null && requestHandler.findControllerAnnotation(Api.class).isPresent())
                .paths(s -> s != null && !s.contains("excel"))
                .build();
    }
}
