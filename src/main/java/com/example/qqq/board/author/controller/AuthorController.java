package com.example.qqq.board.author.controller;


import com.example.qqq.board.author.dtos.AuthorCreateDto;
import com.example.qqq.board.author.dtos.AuthorDetailDtoResponse;
import com.example.qqq.board.author.dtos.AuthorListDtoResponse;
import com.example.qqq.board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
//    1. 의존성 필드 선언
    private AuthorService authorService;
//    2. 생성자를 통한 의존성 주입(수동 생성자 주입)을 이용해 의존 객체(service)를 초기화 //의존 주체(controller) -controller는 service없이 기능을 수행할 수 없다
//     - 컨트롤러가 의존하는 서비스 객체를 생성자를 통해 주입(수동 생성자 주입)한 것
//    2-2. 생성자 주입 -의존성 Spring규칙에 따라 자동으로 주입
    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService =authorService;
    }

//    3. Author(Dto)를 DB로 전달 (entity변환) //사용자->db로 저장
//    param으로 쪼개서 가져오면 안되는 이유=> 지역 변수로 사용하는 것이지 dto를 만들지 못한다 -수동으로 만들수는 있다
//    객체 자동 생성: json ->dto = @RequestBody(json파싱), form // multipart -> @ModelAttribute(setter기반 바인딩)
    @PostMapping("/create")
    @ResponseBody
    public String create(@RequestBody AuthorCreateDto dto){
        authorService.save(dto);
        return "ok";
    }

//    3-2. entity -> dto//관리자 조회화면 ver -비밀번호 빼라
    @GetMapping("/list")
    public List<AuthorListDtoResponse>findAll(){ //service에서 list로 반환해줌
        List<AuthorListDtoResponse>dtoList =authorService.findAll();
        return dtoList;
    }

//    3-3. 회원의 본인회원상세: id조회로 정보 가져오기 //entity -> dto //사용자의 요청(url)에 따라 db->사용자에게 값 전달
    @GetMapping("/{id}")
    public AuthorDetailDtoResponse findById(@PathVariable Long id){
        AuthorDetailDtoResponse dto =authorService.findById(id);
        return dto;
    }

//    3-4
    @DeleteMapping("/{id}")
    public String delet(@PathVariable Long id){
//      안에 기능들은 아직 없다. id검색해서 db에서 바로 지우게 하기. 때문에 db에서 반환할 것이 없어 레파지토리와 서비스에 로직이 없다
        return null;
    }



}
