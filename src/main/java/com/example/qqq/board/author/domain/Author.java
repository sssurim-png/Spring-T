package com.example.qqq.board.author.domain;


import com.example.qqq.board.commom.BaseTimeEntity;
import com.example.qqq.board.post.domain.Address;
import com.example.qqq.board.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.boot.archive.spi.AbstractArchiveDescriptor;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING) //enum
    @Builder.Default
    private Role role = Role.USER;

    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true) //수업용인듯, 원래 여기 안쓰고 repository주입해서 검색한다고 함
    //단점 1. 조건 부착 불가 2.지워지는게 로직으로서 보이지 않는다 3.post전부 로딩 후 지우기 때문에 대용량 시 서버부하 => service에서 각각 로직을 처리하는게 유연성 높아지고, 안전
    @Builder.Default //cascade옵션 쓸 때 초기화 사용
    private List<Post> postList = new ArrayList<>();

    @OneToOne(mappedBy = "author",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //author=저쪽 adderss(java)에서 참조하고 있는 객체명
//    joincoumn의 author_id= fk걸린 것의 DB식 이름
    private Address address;


    public void updatePassword(String password){
        this.password =password;
    }
}
