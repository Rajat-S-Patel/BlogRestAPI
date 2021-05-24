package com.rajat.blogapi.blog;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
;

@Entity
@Table(name = "BLOG")
public class BlogPost {

    @Id
    @Column(name = "post_id")
    private String id;
    @Column(name = "created_on")
    private Date createdOn;
    private Date last_modified;
    private String author;
    private String content;

    public BlogPost() {
    }
    public BlogPost(String id,String author, String content) {
        this.id=id;
        this.author = author;
        this.content = content;
    }
    
    public Date getLast_modified() {
        return last_modified;
    }
    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date created) {
        this.createdOn = created;
        this.last_modified=(Date)createdOn.clone();
    }

    public String getAuthor() {
        return author;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    
}
