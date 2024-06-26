package com.daersh.daersh_project.board.service;

import com.daersh.daersh_project.board.aggregate.Board;
import com.daersh.daersh_project.board.aggregate.BoardFilter;
import com.daersh.daersh_project.board.aggregate.RequestBoard;

import java.util.List;

public interface BoardService {
    int postBoard(RequestBoard req);

    List<Board> getBoardList(BoardFilter filter);
}
