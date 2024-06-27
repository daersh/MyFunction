package com.daersh.daersh_project.likes;


import com.daersh.daersh_project.board.aggregate.Board;
import com.daersh.daersh_project.user.aggregate.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private int likesCode;

    @JoinColumn(name = "user_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "board_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
