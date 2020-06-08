package com.jzsf.tuitor.common.interceptor;

import com.jzsf.tuitor.common.exception.CustomException;
import com.jzsf.tuitor.common.token.Audience;
import com.jzsf.tuitor.common.token.JwtIgnore;
import com.jzsf.tuitor.common.utils.JwtTokenUtil;
import com.jzsf.tuitor.rpcdomain.common.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by plain yuan
 * @since 2020/04/13
 * jwt拦截器
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    private static Audience audience;

    @Autowired
    private Audience injectInstance;

    @PostConstruct
    public void beforeInit() {
        audience = injectInstance;
    }

    public JwtInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }

        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("## authHeader= {}", authHeader);

        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.info("### 用户未登录，请先登录 ###");
            throw new CustomException(ResultCode.USER_NOT_LOGGED_IN);
        }

        // 获取token
        final String token = authHeader.substring(7);
        if (JwtTokenUtil.isExpiration(token)) {
            return false;
        }
        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        JwtTokenUtil.parseJWT(token);
        return true;
    }
}
