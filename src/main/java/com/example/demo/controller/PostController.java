package com.example.demo.controller;

import com.example.demo.dao.PostRepository;
import com.example.demo.entity.Post;
import com.example.demo.exception.PostNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/")
    public ResponseEntity<?> getAllPost(){
        return  ResponseEntity.ok(postRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable int id) throws PostNotFound {
        if(!postRepository.existsById(id))
            throw new PostNotFound("Post not found "+id);
        return  ResponseEntity.ok(postRepository.findById(id));
    }
    @PostMapping("/")
    public ResponseEntity<?> addPost(@Valid @RequestBody Post post){
        return  ResponseEntity.ok(postRepository.save(post));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable int id,@Valid @RequestBody Post post) throws PostNotFound {
        if(!postRepository.existsById(id))
            throw new PostNotFound("Post not found "+id);
        Post post1 = postRepository.findById(id).get();
        post1.setDescription(post.getDescription());
        post1.setImageUrl(post.getImageUrl());
        post1.setTitle(post.getTitle());
        post1.setLikes(post.getLikes());
        return  ResponseEntity.ok(postRepository.save(post1));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable int id) throws PostNotFound {
        Map<String,Boolean> map = new HashMap<>();
        if(!postRepository.existsById(id))
            throw new PostNotFound("Post not found "+id);
        map.put("DELETE",true);
        postRepository.deleteById(id);
        return  ResponseEntity.ok(map);
    }
}
