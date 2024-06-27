package com.daersh.daersh_project.board.aggregate;


import com.daersh.daersh_project.board.BoardDTO;
import com.daersh.daersh_project.config.DateParsing;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBoard {
    private int boardCode;
    private String title;
    private String content;
    private String postDate;
    private String changeDate;
    private int likes;
    private int hits;
    private int userCode;
//    private String userName;

    public ResponseBoard(BoardDTO boardDTO) {
        this.boardCode = boardDTO.getBoardCode();
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
        this.postDate = DateParsing.LdtToStr(boardDTO.getPostDate());
        this.changeDate = DateParsing.LdtToStr(boardDTO.getChangeDate());
        this.likes = boardDTO.getLikes();
        this.hits = boardDTO.getHits();
        this.userCode = boardDTO.getUserCode();
    }
}
