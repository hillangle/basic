package com.zc.basic.auth.config;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.zc.basic.common.utils.ResultUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@AllArgsConstructor
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONUtil.parse(ResultUtils.error(String.valueOf(HttpStatus.HTTP_UNAUTHORIZED),authException.getMessage())).toString());
    }

}
