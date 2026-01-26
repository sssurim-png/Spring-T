package com.example.qqq.board.commom.auth;


import com.example.qqq.board.author.domain.Author;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Signature;
import java.util.Base64;
import java.util.Date;

@Component //서비스기능 보조하는 기능 (use case XX)
public class JwtTokenProvider {

    @Value("${jwt.secretKey}")
    private String st_secret_key;

    private Key secret_key; //Key =method

    @PostConstruct
    public void init(){
        secret_key=new SecretKeySpec(Base64.getDecoder().decode(st_secret_key), SignatureAlgorithm.HS512.getJcaName());
    }

    public String createToken(Author author){
        Claims claims = Jwts.claims().setSubject(author.getEmail());
        claims.put("role",author.getRole().toString());
        Date now =new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+30*60*1000L))
                .signWith(secret_key)
                .compact();
        return token;
    }





}

