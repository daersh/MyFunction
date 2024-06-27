package com.daersh.daersh_project.board.service;

import com.daersh.daersh_project.board.BoardDTO;
import com.daersh.daersh_project.board.aggregate.BoardFilter;
import com.daersh.daersh_project.board.aggregate.RequestBoard;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardService {
    int postBoard(RequestBoard req);

    @Transactional
    int putBoard(RequestBoard req);

    List<BoardDTO> getBoardList(BoardFilter filter);
}
