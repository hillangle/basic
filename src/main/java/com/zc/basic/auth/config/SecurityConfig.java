package com.zc.basic.auth.config;

import com.zc.basic.auth.filter.JWTAuthenticationFilter;
import com.zc.basic.auth.filter.JWTAuthorizationFilter;
import com.zc.basic.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthService authService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
            // 管理后台仅拥有ROLE_SYSUSER权限的用户可以访问
            .antMatchers("/sys/**/**").hasAnyRole("SYSUSER","INPUTUSER","COLLECTUSER","PRICEUSER","PUBLISHUSER")
            // 前台小程序仅拥有ROLE_APPUSER权限的用户可以访问
            .antMatchers("/app/updatePassword").permitAll()
            .antMatchers("/app/**").hasRole("APPUSER")
            .antMatchers("/akm/**").permitAll()
            // 其他都放行了
            .anyRequest().permitAll()
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            // 不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("index.html","/static/**");
    }
}