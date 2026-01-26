package com.example.qqq.board.author.dtos;

import com.example.qqq.board.author.domain.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthorCreateDto {
    @NotBlank(message = "이름이 비어있으면 안됩니다")
    private String name;
    @NotBlank(message = "email이 비어있으면 안됩니다")
    private String email;
    @NotBlank(message = "password는 비어있으면 안됩니다")
    @Size(min=8,message = "패스워드의 길이가 너무 짧습니다")
    private String password;

    public Author toEntity(String encodedPassword) {
        return Author.builder()
                .name(this.name)
                .email(this.email)
                .password(encodedPassword)
                .build();
    }
}