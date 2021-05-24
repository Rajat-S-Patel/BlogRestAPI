package com.rajat.blogapi.blog;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<BlogPost,String> {
    
    @Modifying
    @Transactional
    @Query(name = "updateBlog",value = "UPDATE BLOG SET CONTENT = :content,AUTHOR= :author,LAST_MODIFIED= :last_modified WHERE POST_ID=:post_id",nativeQuery = true)
    public void updateBlog(@Param("content") String content,@Param("author") String author,@Param("last_modified") Date last_modified, @Param("post_id") String post_id);
}
