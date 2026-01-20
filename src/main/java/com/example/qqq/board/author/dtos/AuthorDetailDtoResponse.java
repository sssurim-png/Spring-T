package com.example.qqq.board.author.dtos;



import com.example.qqq.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorDetailDtoResponse {
    private Long id;
    private String name;
    private String password;

    public static AuthorDetailDtoResponse fromEntity(Author author){
        return AuthorDetailDtoResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .password(author.getPassword())
                .build();
    }


    }

