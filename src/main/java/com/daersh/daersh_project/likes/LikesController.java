package com.daersh.daersh_project.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("likes")
public class LikesController {

    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {

        this.likesService = likesService;
    }

    @GetMapping("{boardCode}")
    public ResponseEntity<List<LikesDTO>> getBoardLikes(@PathVariable int boardCode){

        List<LikesDTO> likesDTOList = likesService.getBoardLikes(boardCode);

        return ResponseEntity.ok().body(likesDTOList);
    }

    @PostMapping("{boardCode}")
    public ResponseEntity<String> addLike(@PathVariable int boardCode){

        int result = likesService.addLike(boardCode);

        System.out.println("result = " + result);

        if (result==0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
        return ResponseEntity.status(HttpStatus.OK).body("좋아요 추가되었습니다.");
    }
}
