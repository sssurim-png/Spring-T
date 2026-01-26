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
    private  int postCount;
    private String password;
    private String role;

    public static AuthorDetailDtoResponse fromEntity(Author author){
        return AuthorDetailDtoResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .password(author.getPassword())
                .postCount(author.getPostList().size())
                .role(author.getRole().name()) //enum을 String으로 변환해주는 메서드
                .build();
    }


    }

