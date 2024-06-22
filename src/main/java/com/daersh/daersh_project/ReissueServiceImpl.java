package com.daersh.daersh_project;

import com.daersh.daersh_project.jwt.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReissueServiceImpl implements ReissueService{

    private final JWTUtil jwtUtil;

    @Autowired
    public ReissueServiceImpl(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }


    @Override
    public HttpServletResponse reissue(HttpServletRequest req, HttpServletResponse res) {

        // refresh 토큰 확인
        String refresh = null;
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("refresh")){
                refresh = cookie.getValue();
            }
        }

        // refresh token 검증
        // 1. refresh가 없을 경우
        // 2. 만료된 경우
        // 3. 해당 토큰의 카테고리가 Refresh가 아닌 경우
        if (refresh==null ||
                jwtUtil.isExpired(refresh) ||
                !jwtUtil.getCategory(refresh).equals("refresh")){

            return null;
        }


        // Refresh token 검증 완료 후 새로운 access 토큰 생성
        int userCode = jwtUtil.getUserCode(refresh);
        String userId = jwtUtil.getUserId(refresh);
        String role = jwtUtil.getRole(refresh);

        String access = "Bearer "+ jwtUtil.createJwt("access",userCode,userId,role,600000L);

        // 새로운 access token 헤더에 담기
        res.setHeader("access",access);

        return res;
    }
}
