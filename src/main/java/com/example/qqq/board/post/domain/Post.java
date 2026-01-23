package com.example.qqq.board.post.domain;

import com.example.qqq.board.author.domain.Author;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@Entity
public class Post {
    @Id //fk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Autoincrement
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 3000)
    private String contents;
    private String category;
    @Column(nullable = false)
    private String authorEmail;
    //    @Builder.Default
    private String delYn; //="N"
    @ManyToOne //해줘야 author불러올 수 있다
    private Author author;


    public void updateDelYn() {
        this.delYn = "Y";
    }
}
