package com.daersh.daersh_project.board.aggregate;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestBoard {
    int boardCode;
    String title;
    String content;
}
