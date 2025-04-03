package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List; // Correct import

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example") // Scan all components, services, and controllers
public class WebConfig implements WebMvcConfigurer {  // ✅ Implements WebMvcConfigurer

    @Bean
    public MappingJackson2HttpMessageConverter jsonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {  // ✅ Use extendMessageConverters instead
        converters.add(jsonMessageConverter());
    }
}
