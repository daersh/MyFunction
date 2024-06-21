package com.daersh.daersh_project.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private int userCode;
    @Column(name = "id")
    private String userId;
    @Column(name = "pwd")
    private String userPwd;
    @Column(name = "name")
    private String name;
}
