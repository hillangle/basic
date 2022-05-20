package com.zc.basic.auth.filter;

import cn.hutool.json.JSONUtil;
import com.zc.basic.auth.domain.AuthUser;
import com.zc.basic.auth.service.AuthService;
import com.zc.basic.auth.utils.JwtUtils;
import com.zc.basic.common.exception.BusinessException;
import com.zc.basic.common.exception.enums.BusinessExceptionEnum;
import com.zc.basic.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 验证成功当然就是进行鉴权了
 * 登录成功之后走此类进行鉴权操作
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        UsernamePasswordAuthenticationToken token = getAuthentication(request,tokenHeader);
        if(token != null) {
            SecurityContextHolder.getContext().setAuthentication(token);
            super.doFilterInternal(request, response, chain);
        }else {
            try {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_AUTH_EXCEPTION.getCode(),BusinessExceptionEnum.BUSINESS_AUTH_EXCEPTION.getMsg());
            }catch (BusinessException b){
                log.error(b.getErrorMsg() + b.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getOutputStream().write(JSONUtil.toJsonStr(ResultUtils.error(b.getErrorCode(),b.getErrorMsg())).getBytes());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,String tokenHeader) {
        String token = tokenHeader.replace(JwtUtils.TOKEN_PREFIX, "");
        String username = JwtUtils.getUsername(token);
        String role = JwtUtils.getUserRole(token);
        if(authService == null) {
            BeanFactory factory = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(request.getServletContext());
            authService = (AuthService) factory
                    .getBean("authService");
        }
        AuthUser sysUser = authService.queryUserByAccount(username);
        if(sysUser != null && !sysUser.getUnid().equals("")){
            if (username != null){
                return new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

}