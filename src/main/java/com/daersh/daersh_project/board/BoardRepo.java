package com.daersh.daersh_project.board;

import com.daersh.daersh_project.board.aggregate.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BoardRepo extends JpaRepository<Board,Integer>, JpaSpecificationExecutor<Board> {
}
