package com.daersh.daersh_project.board.service;

import com.daersh.daersh_project.board.BoardDTO;
import com.daersh.daersh_project.board.BoardRepo;
import com.daersh.daersh_project.board.BoardSpecification;
import com.daersh.daersh_project.board.aggregate.Board;
import com.daersh.daersh_project.board.aggregate.BoardFilter;
import com.daersh.daersh_project.board.aggregate.RequestBoard;
import com.daersh.daersh_project.user.aggregate.User;
import com.daersh.daersh_project.user.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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


        try{
            int userCode = getUserCode();
            LocalDateTime localDateTime = LocalDateTime.now();
            User user = new User();
            user.setUserCode(userCode);
            Board board = Board.builder()
                    .title(req.getTitle())
                    .content(req.getContent())
                    .postDate(localDateTime)
                    .changeDate(localDateTime)
                    .deleteDate(null)
                    .hits(0)
                    .likes(0)
                    .user(user)
                    .build();

            boardRepo.save(board);

        }catch (Exception e){
            System.err.println("Post failed");
            return 0;
        }
        return 1;
    }

    @Override
    @Transactional
    public int putBoard(RequestBoard req){

        try {
            int userCode = getUserCode();
            Board board = boardRepo.findById(req.getBoardCode()).orElseThrow();

            if (userCode!= board.getUser().getUserCode())
                throw new Exception();

            board.setTitle(req.getTitle());
            board.setContent(board.getContent());
            board.setChangeDate(LocalDateTime.now());

            boardRepo.save(board);
            return 1;

        }catch (Exception e){
            System.err.println("board put err!");
            return 0;
        }
    }

    private static int getUserCode() throws Exception{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getUserCode();
    }

    @Override
    public List<BoardDTO> getBoardList(BoardFilter filter) {

        PageRequest pageable = PageRequest.of(filter.getPage()-1, 5, Sort.by("postDate").descending());
        List<Board> boardList =  boardRepo.findAll(BoardSpecification.getBoards(filter), pageable).getContent();

        return boardList.stream()
                .map(BoardDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public BoardDTO getBoard(int boardCode) {
        try {
            Board board = boardRepo.findById(boardCode).orElseThrow();
            return new BoardDTO(board);
        }catch (Exception e){
            return null;
        }
    }
}
