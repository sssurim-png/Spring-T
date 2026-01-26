package com.example.qqq.board.post.domain;

import com.example.qqq.board.author.domain.Author;
import com.example.qqq.board.commom.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@Entity
public class Post extends BaseTimeEntity {
    @Id //fk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Autoincrement
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 3000)
    private String contents;
    private String category;
    @Column(nullable = false)
    private String authorEmail;
    @Builder.Default
    private String delYn="N";
    @ManyToOne(fetch = FetchType.LAZY) //해줘야 author불러올 수 있다 -테이블 관계성 설정(n-에서 fk관리)
//    Post를 조회할 때 Author를 “필요할 때만” 가져오게 된다. 보통 이렇게 씀(기본 로딩 전략이 즉시 로딩(EAGER)이라 불필요하게 같이 당겨오는 문제)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), nullable = false) //DB에도 현재 프로그램고 똑같이 테이블 관계설정 해주는 것// 안해도 되지만 db쪽까지 설정하면서 이중으로 안전하게.
    private Author author;



    public void updateDelYn() {
        this.delYn = "Y";
    }
}
