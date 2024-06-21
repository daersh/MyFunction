package com.daersh.daersh_project.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private List<User> getUsers(){
       return userService.getUsers();
    }

    @PostMapping("/regist")
    public String register(@RequestBody RequestUser requestUser){
        boolean result = userService.regist(requestUser);

       return "ok";
    }
}
