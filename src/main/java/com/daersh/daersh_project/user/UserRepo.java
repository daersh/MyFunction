package com.daersh.daersh_project.user;

import com.daersh.daersh_project.user.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsByUserId(String userId);

    User findByUserId(String userId);
}
