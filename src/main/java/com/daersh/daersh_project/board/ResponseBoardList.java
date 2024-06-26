package com.daersh.daersh_project.board;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ResponseBoardList {
    List<ResponseBoard> boardList;
}
