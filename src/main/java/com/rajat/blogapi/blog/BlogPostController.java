package com.rajat.blogapi.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogPostController {
    
    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public ResponseEntity<List<BlogPost>> getAllBlogs(){
        return ResponseEntity.ok().body(blogService.getAllBlogs());
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<BlogPost> getBlogById(@PathVariable String id){
        return ResponseEntity.ok().body(blogService.getBlogById(id));
    }

    @PostMapping("/blogs")
    public ResponseEntity<String> createBlog(@RequestBody BlogPost blogPost){
            blogService.createBlog(blogPost);
            return ResponseEntity.status(HttpStatus.CREATED).body("blog created");
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<String> updateBlog(@RequestBody BlogPost blogPost,@PathVariable String id){
        blogService.updateBlog(blogPost,id);
        return ResponseEntity.ok().body("blog updated");
    }
    
    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<String> deleteBlogById(@PathVariable String id){
        blogService.deleteBlogById(id);
        return ResponseEntity.ok().body("blog deleted");
    }

}
