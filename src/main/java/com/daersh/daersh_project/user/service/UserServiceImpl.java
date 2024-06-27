package com.daersh.daersh_project.user.service;

import com.daersh.daersh_project.user.UserRepo;
import com.daersh.daersh_project.user.aggregate.RequestUserRegist;
import com.daersh.daersh_project.user.aggregate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getUsers() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.err.println("In User Service test.\n userId = " + userId);

        return userRepo.findAll();
    }

    @Override
    @Transactional
    public boolean regist(RequestUserRegist requestUser) {
        String id = requestUser.getUserId();
        String pwd = bCryptPasswordEncoder.encode(requestUser.getUserPwd());
        String name = requestUser.getName();
        String role = requestUser.getRole();
        if(userRepo.existsByUserId(requestUser.getUserId()))
            return false;

        User user = new User(id,pwd,name);
        userRepo.save(user);

        return true;
    }
}
