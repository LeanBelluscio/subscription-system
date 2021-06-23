package com.adidas.email.security;

import com.adidas.email.security.filter.JwtAuthenticationTokenFilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author lbelluscio
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
				.addFilterAfter(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.anyRequest().authenticated();
                
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/swagger-ui.html",
                                   "/swagger-ui.html/**",
                                   "/webjars/**");
    }

}