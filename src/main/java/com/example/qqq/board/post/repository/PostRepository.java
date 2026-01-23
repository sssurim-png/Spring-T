package com.example.qqq.board.post.repository;

import com.example.qqq.board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByDelYn(String delYn);

}
