package com.kotall.learn.rbac.config;

import com.kotall.learn.rbac.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/14 22:39
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl("/admin/login")
                .loginPage("/admin/login.html").permitAll()
                .and()

                .authorizeRequests()
                .antMatchers("/admin/login.html","/admin/login", "/admin/code").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/admin/logout").permitAll()

        ;
//                .and()
//                .logout().logoutSuccessUrl("/admin/login.html");
        http.addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
    }


    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter authenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        return authenticationFilter;
    }

}
