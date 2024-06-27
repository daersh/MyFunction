package com.daersh.daersh_project.user;


import com.daersh.daersh_project.user.aggregate.RequestUserRegist;
import com.daersh.daersh_project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

@RestController
public class UserController {

   private final UserService userService;

   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    private String getUsers(){

        // 현재 사용자(토큰)의 정보를 반환
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "userId: " + userId + "  role: " + role;
    }

    @PostMapping("/regist")
    public ResponseEntity<String> register(@RequestBody@Validated RequestUserRegist requestUser, Errors errors){
       if (errors.hasErrors()) {
           String returnValue="";
           for (int i = 0; i < errors.getAllErrors().size(); i++) {
                returnValue += errors.getAllErrors().get(i).getDefaultMessage()+"\n";
           }
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnValue);
       }
       boolean result = userService.regist(requestUser);
       if (!result)
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       return ResponseEntity.ok().build();
    }

}
