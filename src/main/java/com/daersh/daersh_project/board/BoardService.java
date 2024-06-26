package com.daersh.daersh_project.board;

import java.util.List;

public interface BoardService {
    int postBoard(RequestBoard req);

    List<Board> getBoardList(BoardFilter filter);
}
