package com.example.qqq.board.commom.SecurityConfig;


import com.example.qqq.board.commom.auth.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig { //어떤 인증방식 사용할지
    private JwtTokenFilter jwtTokenFilter;

    public  SecurityConfig(JwtTokenFilter jwtTokenFilter){
        this.jwtTokenFilter = jwtTokenFilter;
    }


    @Bean //스프링이 관리하는 객체로 등록하는 표시-여러군데서 쓸 객체다
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(a->a.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(a->a.requestMatchers("/author/create","/author/login").permitAll().anyRequest().authenticated())
                .build();
    }

    @Bean
    public PasswordEncoder pwEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
