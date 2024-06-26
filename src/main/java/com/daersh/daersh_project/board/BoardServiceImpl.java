package com.daersh.daersh_project.board;

import com.daersh.daersh_project.user.aggregate.User;
import com.daersh.daersh_project.user.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepo boardRepo;

    @Autowired
    public BoardServiceImpl(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }


    @Override
    public int postBoard(RequestBoard req) {
        System.out.println("req = " + req);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        int userCode = user.getUserCode();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        try{
            Board board = Board.builder()
                    .title(req.getTitle())
                    .content(req.getContent())
                    .postDate(localDateTime)
                    .changeDate(localDateTime)
                    .deleteDate(null)
                    .hits(0)
                    .likes(0)
                    .userCode(userCode)
                    .build();
            boardRepo.save(board);
        }catch (Exception e){
            System.err.println("Post failed");
            return 0;
        }
        return 1;
    }
}
