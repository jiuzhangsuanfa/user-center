package com.jiuzhang.usercenter.common.config;

import javax.annotation.Resource;

import com.jiuzhang.usercenter.common.interceptor.CorsInterceptor;
import com.jiuzhang.usercenter.common.interceptor.JwtInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Resource
    private CorsInterceptor corsInteceptor;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截路径可自行配置多个 可用 ，分隔开
        registry.addInterceptor(this.corsInteceptor).addPathPatterns("/**");
        registry.addInterceptor(this.jwtInterceptor).addPathPatterns("/**");
    }

}
