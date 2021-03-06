package com.example.Movie.AuthenticationConfig;

import com.example.Movie.AuthenticationConfig.Service.MovieUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class MovieAuthConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    MovieUserDetailsService movieUserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(movieUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.cors().and().csrf().disable(); /*this will allows other mappings like post and patch.*/
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN").and().formLogin();
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER").and().formLogin();
        http.authorizeRequests().antMatchers("/").permitAll().and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
