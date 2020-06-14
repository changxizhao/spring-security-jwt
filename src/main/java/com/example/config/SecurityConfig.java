package com.example.config;

import com.example.handler.MyAuthenticationFailuerHandler;
import com.example.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author changxizhao
 * @Date 2020/6/13 12:19
 * @Description
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailuerHandler failuerHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
//                .defaultSuccessUrl("/index")
//                .failureUrl("/login.html")
                .successHandler(successHandler)
                .failureHandler(failuerHandler)
                .and()
            .authorizeRequests()
                .antMatchers("/login.html","/login")
                .permitAll()
                .antMatchers("/order")
                .hasAnyAuthority("ROLE_user","ROLE_admin")
                .antMatchers("/system/user","/system/role","/system/menu")
                .hasAnyRole("admin")
                .anyRequest().authenticated()
                .and()
            .csrf().disable();
        http.logout().logoutUrl("/logout");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(bCryptPasswordEncoder().encode("123456"))
                .roles("user")
                .and()
                .withUser("admin")
                .password(bCryptPasswordEncoder().encode("123456"))
                .roles("admin")
                .and()
                .passwordEncoder(bCryptPasswordEncoder());
    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
