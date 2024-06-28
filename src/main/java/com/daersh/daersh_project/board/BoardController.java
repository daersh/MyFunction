package com.daersh.daersh_project.board;

import com.daersh.daersh_project.board.aggregate.*;
import com.daersh.daersh_project.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
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

    // 게시물 페이지의 정보들을 가져옵니다.
    @GetMapping("")
    public ResponseEntity<ResponseBoardList> getBoardList(
            @RequestParam int page,
            @RequestParam int likes,
            @RequestParam int hits,
            @RequestParam int category,
            @RequestParam String search) {

        BoardFilter filter = new BoardFilter(page,likes,hits,category,search);
        if (filter.getPage() <= 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        List<BoardDTO> res = boardService.getBoardList(filter);
        ResponseBoardList responseBoardLists = new ResponseBoardList(res);

        return ResponseEntity.ok(responseBoardLists);
    }

    // 게시물을 상세 조회합니다.
    @GetMapping("{boardCode}")
    public ResponseEntity<ResponseBoard> getBoard(@PathVariable int boardCode){

        BoardDTO res = boardService.getBoard(boardCode);
        if (res==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBoard(res));
    }

    //게시물을 수정합니다.
    @PutMapping("")
    public ResponseEntity<Integer> putBoard(@RequestBody RequestBoard req){

        int result = boardService.putBoard(req);

        if(result==0)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
