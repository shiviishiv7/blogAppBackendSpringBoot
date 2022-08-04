package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostDto implements Serializable {
    private final String title;
    private final String description;
    private final String imageUrl;
    private final int likes;
}
