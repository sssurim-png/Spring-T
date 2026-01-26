package com.example.qqq.board.commom.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtTokenFilter extends GenericFilter {

    @Value("${jwt.secretKey")
    private String st_secret_key;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            String bearerToken = req.getHeader("Authorization"); //토큰 꺼내기
            if (bearerToken == null) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            String token = bearerToken.substring(7);

            Claims claims = Jwts.parserBuilder()//토큰 파싱
                    .setSigningKey(st_secret_key)//서버 키를 암호화 하여 사용자 값과 비교
                    .build()
                    .parseClaimsJws(token)//디코딩 파싱
                    .getBody();

            List<GrantedAuthority> authorties =new ArrayList<>();
            authorties.add(new SimpleGrantedAuthority("ROLE_" + claims.get("role"))); //권한체크 어노테이션
            Authentication authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(),"",authorties); //인증객체 완성
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            e.printStackTrace(); //
        }//try로 안잡아서 놓으면 핸들러가 아니라 500으로 터짐 ->필터에서 예외 발생 → Security가 인지 → AuthenticationEntryPoint 호출 → 401
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
