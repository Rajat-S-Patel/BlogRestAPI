package com.rajat.blogapi.blog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rajat.blogapi.RandomIdGenerator;
import com.rajat.blogapi.exceptions.BlogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<BlogPost> getAllBlogs() {
        List<BlogPost> blogs=new ArrayList<>();
        blogRepository.findAll().forEach(blogs::add);
        return blogs;
    }

    public BlogPost getBlogById(String id) {
       if(!blogRepository.existsById(id)){
            throw new BlogException("Blog doesn't exists");
       }
       return blogRepository.findById(id).get();
    }

    public void createBlog(BlogPost blogPost) {
        blogPost.setId(RandomIdGenerator.getStringId(10));
        // id's are randomly generated hence probability of getting same ids is very low
        // still I have checked if there is same Id or not.
        if(blogRepository.existsById(blogPost.getId())){
            throw new BlogException("Blog with given Id already exists");
        }
        blogPost.setCreatedOn(new Date());
        blogRepository.save(blogPost);
    }

    public void updateBlog(BlogPost blogPost, String id) {
        
        if(!blogRepository.existsById(id)){
            throw new BlogException("Blog doesn't exists");
        }
        blogPost.setId(id);
        blogPost.setLast_modified(new Date());
        blogRepository.updateBlog(blogPost.getContent(), blogPost.getAuthor(),
        blogPost.getLast_modified(),blogPost.getId());
    }

    public void deleteBlogById(String id) {
        if(!blogRepository.existsById(id)){
            throw new BlogException("Blog doesn't exists");
        }
        blogRepository.deleteById(id);
    }
    
}
