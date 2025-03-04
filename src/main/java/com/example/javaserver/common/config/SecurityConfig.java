package com.example.javaserver.common.config;

import com.example.javaserver.common.filter.JwtAuthenticationFilter;
import com.example.javaserver.common.security.CustomAuthenticationFailure;
import com.example.javaserver.common.security.CustomUserDetailsService;
import com.example.javaserver.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomAuthenticationFailure customAuthenticationFailure;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
//                .requestMatchers("/api/auth/**", "/api/dealers/**", "/api/calendar/**").permitAll()
//                .anyRequest().authenticated()
        )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationFailure) // 커스텀 EntryPoint 설정
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenUtil, customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//        return auth.build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
        return authManagerBuilder.build();
    }

}
