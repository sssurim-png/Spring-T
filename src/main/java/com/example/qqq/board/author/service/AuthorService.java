package com.example.qqq.board.author.service;

import com.example.qqq.board.author.domain.Author;
import com.example.qqq.board.author.dtos.*;

import com.example.qqq.board.author.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {
    //의존성 주입
    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //이메일 중복 체크 기능
    public void save(AuthorCreateDto dto) {
        if (authorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 email입니다");
        }
        Author author = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
        authorRepository.save(author);
        //엔티티는 암호화된 비밀번호만 받도록 설계돼 있고,
        //그래서 Service 계층에서
        //Security의 PasswordEncoder를 사용해 값을 만든 뒤
        //그 결과를 매개변수로 전달
    }


    //   회원 모두조회 기능
    public List<AuthorListDtoResponse> findAll() {
        List<Author> authorList = authorRepository.findAll(); //findAll을 불러서 return된걸 담는 것
        List<AuthorListDtoResponse> authorListDtoResponse = new ArrayList<>();
        for (Author a : authorList) {
            AuthorListDtoResponse dto = AuthorListDtoResponse.fromEntity(a);
            authorListDtoResponse.add(dto);
        }
        return authorListDtoResponse;

    }

    /// stream 나중에 보기...


//    회원이 있는지 아이디 찾아보기 기능
    public AuthorDetailDtoResponse findById(Long id) {
        Optional<Author> optAuthor = authorRepository.findById(id);
        Author author = optAuthor.orElseThrow(() -> new NoSuchElementException("entity is not found")); //repository는 값만 넘겨주고 판단은 service에서 한다
        AuthorDetailDtoResponse dto = AuthorDetailDtoResponse.fromEntity(author);
        return dto;
    }

    // 회원이 있는지 아이디로 찾아보고, db에서 삭제 기능
    public void delete(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("entity is not found"));
        authorRepository.delete(author);
    }

    //    아이디 조회하고 없으면 에러, 있으면 삭제
    public void updatePw(AuthorUpdatePwDto dto) {
        Author author = authorRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new EntityNotFoundException("이메일이 없습니다"));
        author.updatePassword(dto.getPassword());
//        Entity를 author로 꺼내서 db까지 안가고 값은 바꿨는데 매서드가 끝나니까 db랑 비교해서 db도 값이 바뀜
    }


    public Author login(AuthorLoginDto dto) {
        Optional<Author> opt_author = authorRepository.findByEmail(dto.getEmail());
        boolean check = true;
        if (!opt_author.isPresent()) {
            check = false;
        } else {
            if (!passwordEncoder.matches(dto.getPassword(), opt_author.get().getPassword()))
                check = false;
        }
        if (!check) {
            throw new IllegalArgumentException("이메일 또는 비밀번호 노일치");
        }
        return opt_author.get();

    }
}
