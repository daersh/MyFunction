package com.daersh.daersh_project.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class boardController {

    private final BoardService boardService;

    @Autowired
    public boardController(BoardService boardService) {
        this.boardService = boardService;
    }

}
