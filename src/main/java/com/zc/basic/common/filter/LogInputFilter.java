package com.zc.basic.common.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Slf4j
public class LogInputFilter extends AbstractRequestLoggingFilter {
    public LogInputFilter(){}

    protected void beforeRequest(HttpServletRequest request, String message) {
//        log.info(message);
    }

    protected void afterRequest(HttpServletRequest request, String message) {
        log.info(message);
    }

    protected String createMessage(HttpServletRequest request, String prefix, String suffix) {
        JSONObject logJson = new JSONObject();
        logJson.set("method", request.getMethod());
        logJson.set("URI", request.getRequestURI());
        if (this.isIncludeQueryString()) {
            if (request.getQueryString() != null) {
                logJson.set("queryString",request.getQueryString());
            }
        }
        String header;
        if (this.isIncludeClientInfo()) {
            if (StringUtils.hasLength(request.getRemoteAddr())) {
                logJson.set("client",request.getRemoteAddr());
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                logJson.set("sessionId", session.getId());
            }
            header = request.getRemoteUser();
            if (header != null) {
                logJson.set("user",header);
            }
        }
        if (this.isIncludeHeaders()) {
            HttpHeaders headers = (new ServletServerHttpRequest(request)).getHeaders();
            if (this.getHeaderPredicate() != null) {
                Enumeration names = request.getHeaderNames();
                while(names.hasMoreElements()) {
                    header = (String)names.nextElement();
                    if (!this.getHeaderPredicate().test(header)) {
                        headers.set(header, "masked");
                    }
                }
            }
            logJson.set("headers",headers.toSingleValueMap());
        }
        if (this.isIncludePayload()) {
            String payload = this.getMessagePayload(request);
            if (payload != null) {
                logJson.set("payload", payload);
            }
        }
        return prefix + JSONUtil.toJsonStr(logJson) + suffix;
    }
}
