package com.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

    @Autowired
    private UserDetailsService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {



      http.httpBasic();
        http.
                csrf().disable();
        http.authorizeRequests().antMatchers("/","/shop/**","/register","/api/register").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest().
                authenticated().and().formLogin().loginPage("/login").permitAll().failureUrl("/login?error=true")

                .defaultSuccessUrl("/").usernameParameter("email").passwordParameter("password").and().oauth2Login().loginPage("/login")
                .successHandler(googleOAuth2SuccessHandler).and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and().exceptionHandling();
        http.headers().frameOptions().disable();
        //.anyRequest().authenticated();


    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**","/static/**","/images/**","/productImages/**","/css/**","/js/**");
    }
}
