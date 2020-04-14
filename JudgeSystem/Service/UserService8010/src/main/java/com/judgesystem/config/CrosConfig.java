package com.judgesystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CrosConfig implements WebMvcConfigurer {
    //解决服务接口跨域调用
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","UPDATE")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
