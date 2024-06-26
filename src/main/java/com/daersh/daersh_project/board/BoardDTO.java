package com.daersh.daersh_project.board;

import com.daersh.daersh_project.board.aggregate.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int boardCode;
    private String title;
    private String content;
    private LocalDateTime postDate;
    private LocalDateTime changeDate;
    private LocalDateTime deleteDate;
    private int likes;
    private int hits;
    private int userCode;

    public BoardDTO(Board board) {
        this.boardCode= board.getBoardCode();
        this.title= board.getTitle();
        this.content= board.getContent();
        this.postDate= board.getPostDate();
        this.changeDate= board.getChangeDate();
        this.deleteDate= board.getDeleteDate();
        this.likes= board.getLikes();
        this.hits= board.getHits();
        this.userCode= board.getUserCode();
    }

    public static BoardDTO from(Board board) {
        return new BoardDTO(board);
    }
}
