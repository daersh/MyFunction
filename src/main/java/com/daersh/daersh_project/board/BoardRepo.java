package com.daersh.daersh_project.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepo extends JpaRepository<Board,Integer>, JpaSpecificationExecutor<Board> {
}
