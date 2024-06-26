package com.daersh.daersh_project.board.aggregate;

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
