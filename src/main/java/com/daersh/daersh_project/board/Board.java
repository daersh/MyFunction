package com.daersh.daersh_project.board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String postDate;
    @Column(name = "change_date")
    private String changeDate;
    @Column(name = "delete_date")
    private String deleteDate;
    @Column(name = "likes")
    private int likes;
    @Column(name = "hits")
    private int hits;

}
