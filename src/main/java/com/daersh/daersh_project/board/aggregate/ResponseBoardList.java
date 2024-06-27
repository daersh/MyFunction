package com.daersh.daersh_project.board.aggregate;

import com.daersh.daersh_project.board.BoardDTO;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
//@Builder
@ToString
public class ResponseBoardList {
    List<ResponseBoard> boardList;

    public ResponseBoardList(List<BoardDTO> res) {
        this.boardList = res.stream().map(ResponseBoard::new).collect(Collectors.toList());
    }
}
