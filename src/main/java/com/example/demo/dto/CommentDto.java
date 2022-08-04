package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDto implements Serializable {
    private final String name;
}
