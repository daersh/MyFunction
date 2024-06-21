package com.daersh.daersh_project.user.service;

import com.daersh.daersh_project.user.aggregate.RequestUser;
import com.daersh.daersh_project.user.aggregate.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    boolean regist(RequestUser requestUser);
}
