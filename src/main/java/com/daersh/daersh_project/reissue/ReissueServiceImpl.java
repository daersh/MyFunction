package com.daersh.daersh_project.reissue;

import com.daersh.daersh_project.jwt.JWTUtil;
import com.daersh.daersh_project.refresh.Refresh;
import com.daersh.daersh_project.refresh.RefreshRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReissueServiceImpl implements ReissueService{

    private final JWTUtil jwtUtil;
    private final RefreshRepo refreshRepo;

    @Autowired
    public ReissueServiceImpl(JWTUtil jwtUtil, RefreshRepo refreshRepo) {

        this.jwtUtil = jwtUtil;
        this.refreshRepo = refreshRepo;
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
        // 4. refresh 토큰이 DB에 존재하는지 검사
        if(!refreshRepo.existsByRefresh(refresh)){

            return null;
        }


        // Refresh token 검증 완료 후 새로운 access 토큰 생성
        int userCode = jwtUtil.getUserCode(refresh);
        String userId = jwtUtil.getUserId(refresh);
        String role = jwtUtil.getRole(refresh);

        // 기존 DB에 저장되어 있는 토큰 제거 후 새로 갱신된 refresh token 저장
        refreshRepo.deleteByRefresh(refresh);
        addRefresh(userId,refresh,86400000L);

        String access = "Bearer "+ jwtUtil.createJwt("access",userCode,userId,role,600000L);

        // 새로운 access token 헤더에 담기
        res.setHeader("access",access);




        return res;
    }

    // Refresh 토큰을 DB에 저장히기 위한 메서드
    private void addRefresh(String userId, String refreshToken , long expireMs){

        Date date = new Date(System.currentTimeMillis()+expireMs);

        Refresh refresh =Refresh.builder()
                .userId(userId)
                .refresh(refreshToken)
                .expiration(date)
                .build();

        refreshRepo.save(refresh);
    }
}


