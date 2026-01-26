package com.example.qqq.board.commom.exception;

import com.example.qqq.board.commom.dtos.CommonErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

//    1) 비즈니스 예외 (ex.email중복일 때)
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<?>illegal(IllegalAccessException e){
        e.printStackTrace();
        CommonErrorDto dto =CommonErrorDto.builder()
                .status_code(400)
                .error_message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }


//    2) 회원가입 (ex.검증예외)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgument(MethodArgumentNotValidException e){
        e.printStackTrace();
        CommonErrorDto dto=CommonErrorDto.builder()
                .status_code(400)
                .error_message(e.getFieldError().getDefaultMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

//    3) 리소스 없음 (ex.id X)
    @ExceptionHandler(NoSuchFieldError.class)
    public ResponseEntity<?> noSuchException(NoSuchFieldError e){
        e.printStackTrace();
        CommonErrorDto dto = CommonErrorDto.builder()
                .status_code(404)
                .error_message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

//    4) 그 외 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>exception(Exception e){
        e.printStackTrace();
        CommonErrorDto dto =CommonErrorDto.builder()
                .status_code(500)
                .error_message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }
}
