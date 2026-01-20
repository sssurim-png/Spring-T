//package com.example.qqq.board.author.service;
//
//import com.example.qqq.board.author.domain.Author;
//import com.example.qqq.board.author.dtos.AuthorCreateDto;
//import com.example.qqq.board.author.dtos.AuthorDetailDtoResponse;
//import com.example.qqq.board.author.dtos.AuthorListDtoReponse;
//import com.example.qqq.board.author.repository.AuthorRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//public class AuthorService_before {
//    private AuthorRepository authorRepository;
////2.
//    public AuthorService_before(){
//        this.authorRepository = new AuthorRepository();
//    }
////3.
//    public void save(AuthorCreateDto  dto){
//        Author author = new Author(null,dto.getName(),dto.getEmail(),dto.getPassword());
//        authorRepository.save(author);
//    }
//
//
////    3-2
//    public List<AuthorListDtoReponse>findAll(){
//        List<Author>authorList =authorRepository.findAll(); //findAll을 불러서 return된걸 담는 것
//        List<AuthorListDtoReponse> authorListDtoResponse = new ArrayList<>();
//        for(Author a : authorList){
//            AuthorListDtoReponse dto =new AuthorListDtoReponse(a.getId(),a.getName(),a.getEmail());
//            authorListDtoResponse.add(dto);
//        }
//        return authorListDtoResponse;
//    }
//
//
////    3-3
//    public AuthorDetailDtoResponse findById(Long id){
//        Optional<Author>optAuthor= authorRepository.findById(id);
//        Author author = optAuthor.orElseThrow(()->new NoSuchElementException("entity is not found")); //repository는 값만 넘겨주고 판단은 service에서 한다
//        AuthorDetailDtoResponse dto =new AuthorDetailDtoResponse(author.getId(), author.getName(), author.getPassword()); //생성자는 개수와 ??만 에러 터뜨려준다. 다른 값을 가져와도 모른다. ex.password생성자로 했는데 email가져와도 모른다
//        return dto;
//    }
//
//}
