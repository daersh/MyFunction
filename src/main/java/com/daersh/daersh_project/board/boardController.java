package com.daersh.daersh_project.board;

import com.daersh.daersh_project.board.aggregate.Board;
import com.daersh.daersh_project.board.aggregate.BoardFilter;
import com.daersh.daersh_project.board.aggregate.RequestBoard;
import com.daersh.daersh_project.board.aggregate.ResponseBoardList;
import com.daersh.daersh_project.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class boardController {

    private final BoardService boardService;

    @Autowired
    public boardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("")
    public ResponseEntity<Integer> postBoard(@RequestBody RequestBoard req){

        int result = boardService.postBoard(req);

        if (result==0){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("")
    public ResponseEntity<ResponseBoardList> getBoardList(@RequestBody BoardFilter filter){

        List<BoardDTO> res = boardService.getBoardList(filter);

        ResponseBoardList responseBoardLists = new ResponseBoardList(res);

        return ResponseEntity.ok(responseBoardLists);
    }

    @PutMapping("")
    public ResponseEntity<Integer> putBoard(@RequestBody RequestBoard req){

        int result = boardService.putBoard(req);

        if(result==0)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
