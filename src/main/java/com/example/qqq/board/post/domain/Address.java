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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String zipCode;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",unique = true,foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),nullable = false)
    private Author author;
}
