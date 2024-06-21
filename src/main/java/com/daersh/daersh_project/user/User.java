package com.daersh.daersh_project.user;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userCode;
    @Column(name = "id")
    private String userId;
    @Column(name = "pwd")
    private String userPwd;
    @Column(name = "name")
    private String name;
    @Column(name = "role")
    private String role;

    public User(String id, String pwd, String name) {
        this.userId = id;
        this.userPwd = pwd;
        this.name = name;
    }
}
