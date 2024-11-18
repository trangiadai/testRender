package org.example.miniforum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Cho phép CORS trên tất cả các endpoint
                .allowedOrigins("*") // Cho phép tất cả các nguồn (origin)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức HTTP được cho phép
                .allowedHeaders("*") // Cho phép tất cả các header
                .allowCredentials(false); // Tắt xác thực nếu không cần cookie
    }
}
