package com.daersh.daersh_project.board;


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
    private LocalDateTime postDate;
    private LocalDateTime changeDate;
    private int likes;
    private int hits;
//    private int userCode;
    private String userName;
}
