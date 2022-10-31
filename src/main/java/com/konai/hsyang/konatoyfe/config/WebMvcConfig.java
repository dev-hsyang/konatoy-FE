package com.konai.hsyang.konatoyfe.config;

import com.konai.hsyang.konatoyfe.config.interceptor.LoginInterceptor;
import com.konai.hsyang.konatoyfe.config.interceptor.MypageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/**",
                        "/mypage/**",
                        "/images/**"
                );
        registry.addInterceptor(new MypageInterceptor())
                .addPathPatterns("/mypage/**")
                .excludePathPatterns("/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/D:/konaToy_images/");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8"); // 파일 인코딩을 UTF-8로 설정
        commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024); // 업로드되는 파일 크기를 제한. 바이트 단위 설정이므로 여기서 5MB 제한을 의미
        return commonsMultipartResolver;
    }
}
