package com.rajat.blogapi.comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENTS")
@IdClass(CommentKey.class)
public class Comment {

    @Id
    private String comment_id;
    @Id
    private String parent_id;
    @Id
    private String post_id;
    private String author;
    private String content;
    private Date created_on;
    private Date last_modified;

    public Comment(String comment_id,String author, String content) {
        this.author = author;
        this.content = content;
        this.comment_id = comment_id;
        this.parent_id = null;
        this.post_id = null;
    }

    public Comment() {

    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
        this.last_modified= (Date) this.created_on.clone();
    }

    public String getParent_id() {
        return parent_id;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }
    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

class CommentKey implements Serializable {
    private String comment_id;
    private String parent_id;
    private String post_id;

    public CommentKey(String comment_id, String parent_id, String post_id) {
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.parent_id = parent_id;
    }

    public CommentKey() {

    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof CommentKey))
            return false;
        if (o == this)
            return true;

        CommentKey obj = (CommentKey) o;
        return (comment_id == obj.comment_id && parent_id == obj.parent_id && post_id == obj.post_id);
    }

}