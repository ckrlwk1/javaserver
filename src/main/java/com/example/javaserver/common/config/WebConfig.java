package com.example.javaserver.common.config;


import com.example.javaserver.common.interceptor.RoleCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RoleCheckInterceptor roleCheckInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**" ) // CORS 설정을 적용할 URL 패턴
                .allowedOrigins("http://192.168.0.51:3000") // React 프론트엔드 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 허용할 헤더
                .exposedHeaders("Authorization")
                .allowCredentials(true); // 쿠키 허용 (필요시)
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleCheckInterceptor)
                .addPathPatterns("/api/calendar/**", "/api/dealer/**"); // 특정 URL에만 인터셉터 적용
    }
}
