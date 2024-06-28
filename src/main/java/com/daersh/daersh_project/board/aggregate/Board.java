package com.daersh.daersh_project.board.aggregate;

import com.daersh.daersh_project.user.aggregate.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "board")
public class Board {
    @Id
    @Column(name = "board_code")
    private int boardCode;


    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "post_date")
    private LocalDateTime postDate;
    @Column(name = "change_date")
    private LocalDateTime changeDate;
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
    @Column(name = "likes")
    private int likes;
    @Column(name = "hits")
    private int hits;
    @JoinColumn(name = "user_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
