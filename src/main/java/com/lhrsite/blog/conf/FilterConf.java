package com.lhrsite.blog.conf;

import com.lhrsite.blog.filter.AdminFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author lhr
 * @create 2018/2/9
 */
@Configuration
public class FilterConf {
    /**
     * 配置过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(adminFilter());
        registration.addUrlPatterns("/admin/*");
        registration.setName("adminFilter");
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "adminFilter")
    public Filter adminFilter() {
        return new AdminFilter();
    }
}
