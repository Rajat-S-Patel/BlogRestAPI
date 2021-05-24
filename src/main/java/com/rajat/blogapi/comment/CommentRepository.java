package com.rajat.blogapi.comment;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,CommentKey> {
   
   @Query(value = "SELECT *FROM COMMENTS WHERE post_id=:post_id",nativeQuery = true)
   public List<Comment> findByPost_id(@Param("post_id") String post_id);
   
   String query="WITH RECURSIVE CMT(COMMENT_ID,PARENT_ID,POST_ID,AUTHOR,CONTENT,CREATED_ON,LAST_MODIFIED) AS("
         + "SELECT COMMENT_ID,PARENT_ID,POST_ID,AUTHOR,CONTENT,CREATED_ON,LAST_MODIFIED FROM COMMENTS WHERE COMMENT_ID=:parent_id "
         + "UNION ALL "
         + "SELECT C.COMMENT_ID, C.PARENT_ID, C.POST_ID, C.AUTHOR, C.CONTENT,C.CREATED_ON, C.LAST_MODIFIED FROM CMT JOIN COMMENTS C ON (CMT.COMMENT_ID = C.PARENT_ID)"
         + ") SELECT *FROM CMT";

   @Query(value = query,nativeQuery = true)
   public List<Comment> findNestedComments(@Param("parent_id") String parent_id);

   @Modifying
   @Transactional
   @Query(name="updateComment",value="UPDATE COMMENTS SET CONTENT = :content, LAST_MODIFIED = :last_modified WHERE COMMENT_ID = :comment_id AND PARENT_ID = :parent_id AND POST_ID = :post_id",nativeQuery = true)
   public void updateComment(@Param("comment_id") String comment_id,
    @Param("parent_id") String parent_id, @Param("post_id") String post_id, @Param("content") String content, @Param("last_modified") Date last_modified);
}
