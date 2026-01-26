package com.example.qqq.board.post.service;

import com.example.qqq.board.author.domain.Author;
import com.example.qqq.board.author.repository.AuthorRepository;
import com.example.qqq.board.post.domain.Post;
import com.example.qqq.board.post.dto.PostCreateDto;
import com.example.qqq.board.post.dto.PostDetailDto;
import com.example.qqq.board.post.dto.PostListDto;
import com.example.qqq.board.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    private PostRepository postRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public PostService(PostRepository postRepository,AuthorRepository authorRepository){
        this.postRepository=postRepository;
        this.authorRepository=authorRepository;
    }

    //   게시글 등록
    public void save(PostCreateDto dto){
        Author author = authorRepository.findByEmail(dto.getAuthorEmail()).orElseThrow(()->new EntityNotFoundException("일치하는 이메일이 없습니다"));
        Post post =dto.toEntity(author);
        postRepository.save(post);

    }


    //    게시글 상세조회//Id로 조회
    public PostDetailDto findById(Long id){
        Optional<Post>optPost= postRepository.findById(id);
        Post post = optPost.orElseThrow(()->new EntityNotFoundException("없는 사용자"));
        PostDetailDto dto= PostDetailDto.fromEntity(post);
        return dto; //id->email로 조회

    }


    //    게시글 목록조회(전체) //N은 빼고 전체 조회

    public List<PostListDto> findAll(){
        List<PostListDto> postListDto = new ArrayList<>();
        List<Post>postList =postRepository.findByDelYn("N"); //안지운거
        for(Post p : postList){
            PostListDto dto = PostListDto.fromEntity(p);
            postListDto.add(dto);
        }
        return postListDto;



    }


//    게시글 업데이트
    public void updateDelYn(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 회원입니다"));

        post.updateDelYn();
    }
}