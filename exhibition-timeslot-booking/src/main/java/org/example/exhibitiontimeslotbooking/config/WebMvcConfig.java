package org.example.exhibitiontimeslotbooking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.base-path}")
    private String localPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/");

        // 프론트엔드에서 사용할때 매핑시켜주기 위해서 사용
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(localPath + "/");
    }
}