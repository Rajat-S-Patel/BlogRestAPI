package com.rajat.blogapi.comment;

import java.util.Date;
import java.util.List;

import com.rajat.blogapi.RandomIdGenerator;
import com.rajat.blogapi.exceptions.CommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllCommentsForPost(String post_id) {
        return commentRepository.findByPost_id(post_id);   
    }

    public void createNewCommentOnPost(Comment comment, String post_id) {
        comment.setComment_id(RandomIdGenerator.getStringId(10));
        comment.setParent_id(post_id);
        comment.setPost_id(post_id);
       
       if(commentRepository.existsById(new CommentKey(comment.getComment_id(),comment.getParent_id(),comment.getPost_id()))){
           throw new CommentException("comment with given Id already exists");
       }
       comment.setCreated_on(new Date());
       commentRepository.save(comment);
    }

    public List<Comment> findNestedComments(String parent_id){
        return commentRepository.findNestedComments(parent_id);
    }

    public void createNestedComment(Comment comment, String parent_id, String post_id) {
        comment.setComment_id(RandomIdGenerator.getStringId(10));
        comment.setPost_id(post_id);
        comment.setParent_id(parent_id);

        if(commentRepository.existsById(new CommentKey(comment.getComment_id(), parent_id, post_id))){
            throw new CommentException("comment with given Id already exists");
        }
        comment.setCreated_on(new Date());
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment, String post_id, String comment_id) {
        comment.setComment_id(comment_id);
        comment.setParent_id(post_id);
        comment.setPost_id(post_id);
        if (!commentRepository.existsById(new CommentKey(comment.getComment_id(), comment.getParent_id(), comment.getPost_id()))) {
            throw new CommentException("comment with given Id doesn't exists");
        }
        comment.setLast_modified(new Date());
        commentRepository.updateComment(comment.getComment_id(),comment.getParent_id(),comment.getPost_id(),
        comment.getContent(),comment.getLast_modified());
    }

    public void updateNestedComment(Comment comment, String post_id, String parent_id, String comment_id) {
        comment.setComment_id(comment_id);
        comment.setParent_id(parent_id);
        comment.setPost_id(post_id);
        if (!commentRepository.existsById(new CommentKey(comment.getComment_id(), comment.getParent_id(), comment.getPost_id()))) {
            throw new CommentException("comment with given Id doesn't exists");
        }
        comment.setLast_modified(new Date());
        commentRepository.updateComment(comment.getComment_id(),comment.getParent_id(),comment.getPost_id(),
        comment.getContent(),comment.getLast_modified());
    }

}
