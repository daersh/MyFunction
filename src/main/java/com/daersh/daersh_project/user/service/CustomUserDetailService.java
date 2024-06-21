package com.daersh.daersh_project.user.service;

import com.daersh.daersh_project.user.UserRepo;
import com.daersh.daersh_project.user.aggregate.User;
import com.daersh.daersh_project.user.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = userRepo.findByUserId(userId);

        if(user != null){
            return new CustomUserDetails(user);
        }

        return null;

    }
}
