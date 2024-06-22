package com.daersh.daersh_project.jwt;
import com.daersh.daersh_project.user.aggregate.User;
import com.daersh.daersh_project.user.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


// 요청에 대해서 한번만 받는 필터 extend
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 헤더 에 키값 빼오기
        String authorization = request.getHeader("Authorization");

        // 토큰 존재여부 및 접두사 확인
        if (authorization==null || !authorization.startsWith("Bearer ")){
            System.err.println("token not exist!");
            filterChain.doFilter(request,response);
            return;
        }

        String token = authorization.split(" ")[1];

        // 토큰 시간 검증
        if (jwtUtil.isExpired(token)){
            System.err.println("expired token!");
            filterChain.doFilter(request,response);
            return;
        }

        // 세션 생성
        // 토큰 정보 해석
        String userId = jwtUtil.getUserId(token);
        Integer userCode = jwtUtil.getUserCode(token);
        String role = jwtUtil.getRole(token);
        System.out.println("userId: "+userId +" userCode: "+userCode +" role: "+role );

        new User();
        User user = User.builder()
                .userId(userId)
                .role(role)
                .build();

        // userdeails에 유저 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);
    }


}
