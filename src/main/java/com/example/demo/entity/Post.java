package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotEmpty(message = "title can't be empty")
    private String title;
    @NotEmpty(message = "description can't be empty")
    private String description;
    @NotEmpty(message = "imageURL can't be empty")
    private String imageUrl;
    @NotEmpty(message = "videoURL can't be empty")
    private String videoUrl;
    @Min(value = 0)
    private int likes;
}