package com.example.qqq.board.post.controller;

import com.example.qqq.board.post.dto.PostCreateDto;
import com.example.qqq.board.post.dto.PostDetailDto;
import com.example.qqq.board.post.dto.PostListDto;
import com.example.qqq.board.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postservice;
    @Autowired
    public PostController(PostService postService){
        this.postservice=postService;
    }

//   게시글 등록
    @PostMapping("/create")
    @ResponseBody
    public void create(@RequestBody PostCreateDto dto){
        postservice.save(dto);

    }

//    게시글 상세조회
    @GetMapping("/{id}")
    public PostDetailDto findById(@PathVariable Long id){
        PostDetailDto dto = postservice.findById(id);
        return dto;
    }

//    게시글 목록조회(전체)
    @GetMapping("/posts")
    public List<PostListDto> findByDelYn(){
        List<PostListDto> dto= postservice.findAll();
        return dto;
    }

//    게시글 업데이트
    @DeleteMapping("/{id}")
    public void updateDelYn(@PathVariable Long id){
        postservice.updateDelYn(id);

    }

}
