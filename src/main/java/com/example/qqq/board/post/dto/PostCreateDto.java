package com.example.qqq.board.post.dto;

import com.example.qqq.board.author.domain.Author;
import com.example.qqq.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateDto {
    private String title;
    private String contents;
    private String category;
    private String authorEmail; //찾는용

    public Post toEntity(Author author){
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .category(this.category)
                .author(author)//DB반환용(id)// Email을 가지고 id를 찾아오는 것-spring이 pk걸린값(id)로 자동변환해준다. entity가 되려는 것
//                .delYn("N") =>받아오는값을 지정을 해놓는다는것이 어폐가 있다
                .build();
    }

}
