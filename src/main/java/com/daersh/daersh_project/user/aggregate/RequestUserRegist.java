package com.daersh.daersh_project.user.aggregate;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class RequestUserRegist {

    @NotEmpty(message = "아이디 입력하시오.")
    private String userId;

    @NotEmpty(message = "패스워드 입력하시오.")
    @Size(min = 8,max = 15, message = "8자 이상, 15글자 이하의 비밀번호만 가능합니다.")
    private String userPwd;

    @NotEmpty(message = "email 입력하시오.")
    @Email(message = "이메일 형식 불일치")
    private String email;

    @NotEmpty(message = "이름 입력하시오.")
    private String name;

    @NotEmpty(message = "역할 입력하시오.")
    @AssertTrue( message = "역할 에러")
    private String role;


}
