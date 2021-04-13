package com.ryan.portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭csrf防护
                .csrf().disable()
                .headers().frameOptions().disable()
                .and();
//        http
//                //登录处理
//                .formLogin() //表单方式，或httpBasic
//                .loginPage("/login")
//                .loginProcessingUrl("/form")
//                .defaultSuccessUrl("/table") //成功登陆后跳转页面
//                .failureUrl("/404")
//                .permitAll()
//                .and();
        http
                .authorizeRequests() // 授权配置
                //无需权限访问
                .antMatchers( "/login/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                //其他接口需要登录后才能访问
                .anyRequest().authenticated()
                .and();
    }

    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许证书
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }
}

