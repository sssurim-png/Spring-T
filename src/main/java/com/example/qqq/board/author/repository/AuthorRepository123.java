//package com.example.qqq.board.author.repository;
//
//import ch.qos.logback.core.read.ListAppender;
//import com.example.qqq.board.author.domain.Author;
//import com.example.qqq.board.author.service.AuthorService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class AuthorRepository {
////    1.
//    private List<Author>authorList =new ArrayList<>();
////    2.
//    private static Long staticId =1L; //DB가 없기 때문에 setter설정해서 id값 올리는 것. 원래 db가 한다
////3.
//    public void save(Author author){
//        this.authorList.add(author);
//        author.setId(staticId++);
//    }
//
////원래는
////public class AuthorRepository {
////    public void save(Author author){
////        this.authorList.add(author); <-여기가 insert문 sql로 넣는다
////    }
////이렇게만 존재한다 -> 이렇게 db넘어가고 id생성
//
//
//
////    3-2
//    public List<Author>findAll(){
//        return this.authorList;
//    }
//
//
//
//
//
////3-3
//    public Optional<Author>findById(Long id){
//        Author author =null;
//        for(Author a: this.authorList){
//            if(a.getId().equals(id)){
//                author=a;
//            }
//        }
//        return Optional.ofNullable(author);
//    }
//
//}//여기도 원래 sql문
