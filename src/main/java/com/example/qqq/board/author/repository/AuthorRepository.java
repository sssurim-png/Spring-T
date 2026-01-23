package com.example.qqq.board.author.repository;

import com.example.qqq.board.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

//    이메일 조회 // 나머지는 spring구현
    Optional<Author> findByEmail(String email);

}
