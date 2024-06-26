package com.daersh.daersh_project.board.aggregate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardFilter {
    int page;
    int likes;
    int hits;
    // 0 : title + content
    // 1: title
    // 2: content
    // 3: userName
    int category;
    String search;
}
