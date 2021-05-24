package com.rajat.blogapi.comment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @GetMapping("/blogs/{post_id}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsForPost(@PathVariable String post_id){
        return ResponseEntity.ok().body(commentService.getAllCommentsForPost(post_id)); 
    }

    @PostMapping("/blogs/{post_id}/comments")
    public ResponseEntity<String> createNewCommentOnPost(@RequestBody Comment comment,@PathVariable String post_id){
        commentService.createNewCommentOnPost(comment,post_id);
        return ResponseEntity.status(HttpStatus.CREATED).body("comment created");
    }

    @GetMapping("/blogs/{post_id}/comments/{comment_id}")
    public ResponseEntity<List<Comment>> getAllCommentsUnderComment(@PathVariable String post_id,@PathVariable String comment_id){
        return ResponseEntity.ok().body(commentService.findNestedComments(comment_id));
    }

    @PostMapping("/blogs/{post_id}/comments/{parent_id}")
    public ResponseEntity<String> createNestedComment(@RequestBody Comment comment,@PathVariable String post_id,
    @PathVariable String parent_id){
        commentService.createNestedComment(comment,parent_id,post_id);
        return ResponseEntity.status(HttpStatus.CREATED).body("comment created");
    }
    
    @PutMapping("/blogs/{post_id}/comments/{comment_id}")
    public ResponseEntity<String> updateComment(@RequestBody Comment comment,@PathVariable String post_id,@PathVariable String comment_id){
        commentService.updateComment(comment,post_id,comment_id); 
        return ResponseEntity.ok().body("updated successfully"); 
    }

    @PutMapping("/blogs/{post_id}/comments/{parent_id}/{comment_id}")
    public ResponseEntity<String> updateNestedComment(@RequestBody Comment comment, @PathVariable String post_id,@PathVariable String parent_id,@PathVariable String comment_id){
        commentService.updateNestedComment(comment,post_id,parent_id,comment_id);
        return ResponseEntity.ok().body("updated successfully");
    }

}
