package com.rajat.blogapi;

import java.util.Date;
import com.rajat.blogapi.exceptions.BlogException;
import com.rajat.blogapi.exceptions.ErrorMessage;
import com.rajat.blogapi.exceptions.UserException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class HandleExceptions {
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorMessage> handleApiError(NoHandlerFoundException e){
        ErrorMessage m = new ErrorMessage(e.getMessage(),new Date(),HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleInternalServerError(RuntimeException e){
        e.printStackTrace();
        ErrorMessage m = new ErrorMessage("Something went wrong!!", new Date(),HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(m);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorMessage> handleUserExceptions(UserException e){
        ErrorMessage m = new ErrorMessage(e.getMessage(),new Date(),HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(m);
    }

    @ExceptionHandler(BlogException.class)
    public ResponseEntity<ErrorMessage> handleBlogExceptions(BlogException e){
        ErrorMessage m=new ErrorMessage(e.getMessage(),new Date(),HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(m);
    }

    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleSqlExceptions(DataIntegrityViolationException e){
        ErrorMessage m = new ErrorMessage("SQL contraint violation",new Date(),HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(m);
    }

}