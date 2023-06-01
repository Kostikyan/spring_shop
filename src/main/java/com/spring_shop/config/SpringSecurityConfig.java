package com.spring_shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String admin = "ADMIN";
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers("/user/admin").hasAuthority(admin)
                .requestMatchers("/add/category/page").hasAuthority(admin)
                .requestMatchers("/add/product/page").hasAuthority(admin)
                .requestMatchers(HttpMethod.POST, "/add/product").hasAuthority(admin)
                .requestMatchers(HttpMethod.POST, "/add/category").hasAuthority(admin)
                .requestMatchers("/user/admin").hasAuthority("ADMIN")
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/images/**").permitAll()
                .requestMatchers("/js/**").permitAll()
                .requestMatchers("/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .defaultSuccessUrl("/customSuccessLogin")
                .loginProcessingUrl("/login").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/loginPage")
                .permitAll();

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(passwordEncoder);
        return dao;
    }

}
