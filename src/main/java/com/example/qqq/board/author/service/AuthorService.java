package com.example.qqq.board.author.service;

import com.example.qqq.board.author.domain.Author;
import com.example.qqq.board.author.dtos.AuthorCreateDto;
import com.example.qqq.board.author.dtos.AuthorDetailDtoResponse;
import com.example.qqq.board.author.dtos.AuthorListDtoResponse;
import com.example.qqq.board.author.repository.AuthorJdbcRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorJdbcRepository authorJdbcRepository;
    @Autowired
    public AuthorService(AuthorJdbcRepository authorJdbcRepository){
        this.authorJdbcRepository = authorJdbcRepository;
    }
//3.
    public void save(AuthorCreateDto dto){
        Author author = dto.toEntity();
        authorJdbcRepository.save(author);
    }


//    3-2
    public List<AuthorListDtoResponse>findAll(){
        List<Author>authorList = authorJdbcRepository.findAll(); //findAll을 불러서 return된걸 담는 것
        List<AuthorListDtoResponse> authorListDtoResponse = new ArrayList<>();
        for(Author a : authorList){
            AuthorListDtoResponse dto = AuthorListDtoResponse.fromEntity(a);
            authorListDtoResponse.add(dto);
        }
        return authorListDtoResponse;
    }

    /// stream 나중에 보기...


//    3-3
    public AuthorDetailDtoResponse findById(Long id){
        Optional<Author>optAuthor= authorJdbcRepository.findById(id);
        Author author = optAuthor.orElseThrow(()->new NoSuchElementException("entity is not found")); //repository는 값만 넘겨주고 판단은 service에서 한다
        AuthorDetailDtoResponse dto = AuthorDetailDtoResponse.fromEntity(author);
        return dto;
    }

}
