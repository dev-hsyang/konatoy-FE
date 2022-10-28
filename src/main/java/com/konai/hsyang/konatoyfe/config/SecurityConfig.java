package com.konai.hsyang.konatoyfe.config;

import com.konai.hsyang.konatoyfe.config.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PrincipalDetailsService principalDetailsService;

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers( "/favicon.ico", "/resources/**", "/error", "/static/**", "/docs/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 로그인 처리를 하기 위한 AuthenticationManagerBuilder를 설정
        auth.userDetailsService(principalDetailsService).passwordEncoder(encodePwd());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/joinForm",
                        "/loginForm",
                        "/api/**",
                        "/sample"
                )
                .permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                //.successHandler(new LoginSuccessHandler())
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/loginForm");
    }
}
