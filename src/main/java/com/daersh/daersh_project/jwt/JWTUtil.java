package com.daersh.daersh_project.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")
                    String settingKey){
        secretKey = new SecretKeySpec(
                settingKey.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm()
        );
    }

    public String getUserId(String token){
        return getPayload(token)
                .get("userId",String.class);
    }

    public Integer getUserCode(String token){
        return getPayload(token)
                .get("userCode",Integer.class);
    }

    public String getRole(String token) {
        return getPayload(token)
                .get("role",String.class);
    }

    private Claims getPayload(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String getCategory(String token){
        return getPayload(token).get("category", String.class);
    }

    // 토큰 만료 시간 확인
    public boolean isExpired(String token){
        return getPayload(token)
                .getExpiration().before(new Date());
    }



    // 로그인 성공 후 유저 정보를 토큰으로 만들어 반환하는 메서드
    public String createJwt(String category,int userCode,String userId, String role, Long expiredMs){
        return Jwts.builder()
                .claim("category",category)         // access인지 refresh인지 판단하기 위한 카테고리
                .claim("userCode",userCode)
                .claim("userId",userId)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiredMs))
                .signWith(secretKey)
                .compact();
    }

}
