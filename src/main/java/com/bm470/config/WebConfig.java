package com.bm470.config;

import com.bm470.interceptor.LogInterceptor;
import com.bm470.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = "com.bm470")
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public InternalResourceViewResolver jspResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("text", "plain", StandardCharsets.UTF_8));
        mediaTypes.add(new MediaType("text", "html", StandardCharsets.UTF_8));
        mediaTypes.add(new MediaType("application", "json", StandardCharsets.UTF_8));
        mediaTypes.add(new MediaType("text", "javascript", StandardCharsets.UTF_8));

        stringHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(stringHttpMessageConverter);

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

      registry.addInterceptor(new SessionInterceptor())
              .addPathPatterns("/*").excludePathPatterns("/").excludePathPatterns("/loginKontrol");
            registry.addInterceptor(new LogInterceptor()).addPathPatterns("/*");
    }




}
