package com.daersh.daersh_project.board.service;

import com.daersh.daersh_project.board.BoardDTO;
import com.daersh.daersh_project.board.BoardRepo;
import com.daersh.daersh_project.board.BoardSpecification;
import com.daersh.daersh_project.board.aggregate.Board;
import com.daersh.daersh_project.board.aggregate.BoardFilter;
import com.daersh.daersh_project.board.aggregate.RequestBoard;
import com.daersh.daersh_project.user.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepo boardRepo;

    @Autowired
    public BoardServiceImpl(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }


    @Override
    @Transactional
    public int postBoard(RequestBoard req) {
        System.out.println("req = " + req);
        int userCode = getUserCode();
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

    private static int getUserCode() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getUserCode();
    }

    @Override
    public List<Board> getBoardList(BoardFilter filter) {
        System.out.println("page = " + filter.getPage());
        System.out.println("filter = " + filter);
        PageRequest pageable = PageRequest.of(filter.getPage()-1, 5, Sort.by("postDate").descending());
        Page<BoardDTO> boardList = boardRepo.findAll(pageable).map(BoardDTO::from);
        return boardRepo.findAll(BoardSpecification.getBoards(filter), pageable).getContent();
//        return boardList;
    }
}
