package com.daersh.daersh_project.likes;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LikesDTO {
    private int likesCode;
    private int boardCode;
    private int userCode;
    private String userName;

    public LikesDTO(Likes likes) {
        this.likesCode = likes.getLikesCode();
        this.boardCode = likes.getBoard().getBoardCode();
        this.userCode = likes.getUser().getUserCode();
        this.userName = likes.getUser().getName();
    }

}
