package com.daersh.daersh_project.user.aggregate;

import lombok.Getter;

@Getter
public class RequestUser {
    private int userCode;
    private String userId;
    private String userPwd;
    private String name;
    private String role;
}