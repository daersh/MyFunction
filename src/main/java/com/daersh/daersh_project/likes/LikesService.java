package com.daersh.daersh_project.likes;

import java.util.List;

public interface LikesService {
    List<LikesDTO> getBoardLikes(int boardCode);

    int addLike(int boardCode);
}
