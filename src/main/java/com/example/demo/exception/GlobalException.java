package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(CommentNotFound.class)
    public ResponseEntity<?> commentNotFound(CommentNotFound commentNotFound){
        Map<String,String>map = new HashMap<>();
        map.put("Error",commentNotFound.getMessage());
        return ResponseEntity.badRequest().body(map);
    }


    @ExceptionHandler(PostNotFound.class)
    public ResponseEntity<?> postNotFoundException(PostNotFound postNotFound){
        Map<String,String>map = new HashMap<>();
        map.put("Error",postNotFound.getMessage());
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String, String> error = new HashMap<>();
        methodArgumentNotValidException.getAllErrors()
                .forEach(objectError -> {
                    String field = ((FieldError) objectError).getField();
                    String defaultMessage = objectError.getDefaultMessage();
                    error.put(field,defaultMessage);
                });
        return ResponseEntity.badRequest().body(error);
    }

}
