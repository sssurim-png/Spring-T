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
    private String authorEmail;

    public Post toEntity(Author author){
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .category(this.category)
                .author(author)// postTest에 넣고, entity조립에 넣기
                .delYn("N")
                .build();
    }

}
