package com.daersh.daersh_project.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class boardController {

    private final BoardService boardService;

    @Autowired
    public boardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/")
    public ResponseEntity<Integer> postBoard(@RequestBody RequestBoard req){
        System.out.println("asdfasdf");
        int result = boardService.postBoard(req);
        if (result==0){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
