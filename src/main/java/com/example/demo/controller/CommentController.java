package com.example.demo.controller;

import com.example.demo.dao.CommentRepository;
import com.example.demo.dao.PostRepository;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.exception.CommentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @GetMapping("/")
    public ResponseEntity<?> getAllComment(){
        return  ResponseEntity.ok(commentRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable int id) throws CommentNotFound {
        if(!commentRepository.existsById(id))
            throw new CommentNotFound("Comment not found "+id);
        return  ResponseEntity.ok(commentRepository.findById(id));
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> addComment(@PathVariable int id, @Valid @RequestBody Comment comment) throws CommentNotFound {
        if(!postRepository.existsById(id))
            throw new CommentNotFound("Comment not found "+id);
        Post post = postRepository.findById(id).get();
        comment.setPost(post);
        return  ResponseEntity.ok(commentRepository.save(comment));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable int id,@Valid @RequestBody Comment comment) throws CommentNotFound {
        if(!commentRepository.existsById(id))
            throw new CommentNotFound("Comment not found "+id);
        Comment comment1 = commentRepository.findById(id).get();
        comment1.setName(comment.getName());
        return  ResponseEntity.ok(commentRepository.save(comment1));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable int id) throws CommentNotFound {
        Map<String,Boolean> map = new HashMap<>();
        if(!commentRepository.existsById(id))
            throw new CommentNotFound("Comment not found "+id);
        map.put("DELETE",true);
        commentRepository.deleteById(id);
        return  ResponseEntity.ok(map);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getAllCommentByPostId(@PathVariable int postId){
        List<Comment> allCommentByPostId = commentRepository.findAllCommentByPostId(postId);
        return ResponseEntity.ok(allCommentByPostId);
    }
}
