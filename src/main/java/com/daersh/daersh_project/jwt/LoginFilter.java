package com.daersh.daersh_project.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    // authenticationManager:검증 담당
    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res)
            throws AuthenticationException
    {
        // id , pwd 추출
        String userId = obtainUsername(req);
        String userPwd = obtainPassword(req);

        System.out.println("userId = " + userId);
        System.out.println("userPwd = " + userPwd);

        // 시큐리티에서 아이디, 비밀번호를 검증하기 위해서는 토큰에 담아야한다.
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId,userPwd, null);

        //토큰에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(token);

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(req, res, chain, authResult);
        System.out.println("login success");

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(req, res, failed);
        System.out.println("login failed");
    }
}
