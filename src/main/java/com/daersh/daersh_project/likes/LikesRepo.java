package com.daersh.daersh_project.likes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepo extends JpaRepository<Likes,Integer> {

    List<Likes> findAllByBoardBoardCode(int boardCode);
}
