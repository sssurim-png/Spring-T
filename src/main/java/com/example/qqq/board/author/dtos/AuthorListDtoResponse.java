package com.example.qqq.board.author.dtos;


import com.example.qqq.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorListDtoResponse {
    private Long id;
    private String name;
    private String email;

    public static AuthorListDtoResponse fromEntity(Author author){
        return AuthorListDtoResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .build();

    }


}