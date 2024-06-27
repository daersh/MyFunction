package com.daersh.daersh_project.likes;

import com.daersh.daersh_project.board.aggregate.Board;
import com.daersh.daersh_project.user.aggregate.User;
import com.daersh.daersh_project.user.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikesServiceImpl implements LikesService{

    private final LikesRepo likesRepo;

    @Autowired
    public LikesServiceImpl(LikesRepo likesRepo) {

        this.likesRepo = likesRepo;
    }

    private static int getUserCode() throws Exception{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getUserCode();
    }

    @Override
    public List<LikesDTO> getBoardLikes(int boardCode) {

        List<Likes> likesList = likesRepo.findAllByBoardBoardCode(boardCode);
        if (likesList.isEmpty()){
            return Collections.emptyList();
        }

        return likesList.stream().map(LikesDTO::new).toList();
    }

    @Override
    public int addLike(int boardCode) {

        try {
            int userCode = getUserCode();

            // Likes 객체 생성을 위한 로직
            User user = User.builder()
                    .userCode(userCode)
                    .build();
            Board board = Board.builder()
                    .boardCode(boardCode)
                    .build();

            Likes likes = Likes.builder()
                    .board(board)
                    .user(user)
                    .build();

            likesRepo.save(likes);
            return 1;
        }catch (Exception e){
            return 0;
        }

    }
}
