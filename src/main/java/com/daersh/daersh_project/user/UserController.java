package com.daersh.daersh_project.user;


import com.daersh.daersh_project.jwt.JWTFilter;
import com.daersh.daersh_project.user.aggregate.RequestUser;
import com.daersh.daersh_project.user.aggregate.User;
import com.daersh.daersh_project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@ResponseBody
public class UserController {

   private final UserService userService;

   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    private String getUsers(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "userId: " + userId + "  role: " + role;
    }

    @PostMapping("/regist")
    public String register(@RequestBody RequestUser requestUser){
        boolean result = userService.regist(requestUser);

       return "ok";
    }

}
