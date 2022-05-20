package com.zc.basic.common.config;

import com.zc.basic.common.filter.LogInputFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogInputConfig {
    @Bean
    public LogInputFilter requestLoggingFilter(){
        LogInputFilter loggingFilter = new LogInputFilter();
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(2000);
        loggingFilter.setAfterMessagePrefix("{\"Request\":");
        loggingFilter.setAfterMessageSuffix("}");
        return loggingFilter;
    }

}
