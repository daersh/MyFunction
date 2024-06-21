package com.daersh.daersh_project.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepo boardRepo;

    @Autowired
    public BoardServiceImpl(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }


}
