package com.security.learn.mvc.config;

import com.security.learn.mvc.filter.TimeFilter;
import com.security.learn.mvc.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * create by： harry
 * date:  2019/11/24 0024 下午 3:09
 **/
/*
@Configuration
public class WebConfig extends WebMvcConfigurationSupport{

    @Autowired
    private TimeInterceptor timeInterceptor;


    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(2000);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
      //  registry.addInterceptor(timeInterceptor);
    }

   // @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TimeFilter());
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }
}
*/
