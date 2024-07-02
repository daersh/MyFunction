package com.daersh.daersh_project.config;

import com.daersh.daersh_project.jwt.JWTFilter;
import com.daersh.daersh_project.jwt.JWTUtil;
import com.daersh.daersh_project.jwt.LoginFilter;
import com.daersh.daersh_project.jwt.CustomLogoutFilter;
import com.daersh.daersh_project.refresh.RefreshRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final RefreshRepo refreshRepo;

    @Autowired
    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil, RefreshRepo refreshRepo) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.refreshRepo = refreshRepo;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.cors((cors)->cors
                .configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "https://daersh.netlify.app")); // 두 도메인 모두 허용
//                        configuration.setAllowedOrigins(Collections.singletonList("*")); // 모든 도메인 허용
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);
                        configuration.setExposedHeaders(Collections.singletonList("access"));
                        return configuration;
                    }
                })
        );    

        // csrf disable 설정: jwt 방식 에서는 세션을 stateless 상태로 관리하여 방어하지 않아도 되어 disable 시켜도 된다.
        // jwt 방식으로 로그인 하기 때문에 form 로그인, basic 인증 방식 disable
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        // 경로별 인가 설정
        httpSecurity
                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/login","/","/regist","/reissue").permitAll()
//                        .requestMatchers("/user").hasRole("ADMIN")
//                        .requestMatchers("/board").hasAnyRole("USER","ADMIN")
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()
                );

        // filter 추가
        httpSecurity
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class)
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepo), LogoutFilter.class)
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, refreshRepo), UsernamePasswordAuthenticationFilter.class);

        // 세션설정
        // jwt 방식에서는 세션을 stateless로 관리하기 때문에 이를 설정해야한다.
        httpSecurity
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }
}
