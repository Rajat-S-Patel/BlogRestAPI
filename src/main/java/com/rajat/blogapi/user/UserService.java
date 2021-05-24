package com.rajat.blogapi.user;
import com.rajat.blogapi.exceptions.UserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        if(userRepository.existsById(user.getEmail())){
            throw new UserException("User already exists");
        }
        userRepository.save(user);
    }

    public User getUser(String email) {
        if(!userRepository.existsById(email)){
            throw new UserException("User doesn't exists");
        }
        return userRepository.findById(email).get();
    }

    public void updateUser(User user, String email) {
        if(!userRepository.existsById(email)){
            throw new UserException("User doesn't exists");
        }
        userRepository.save(user);
    }

    public void deleteUser(String email) {
        if(!userRepository.existsById(email)){
            throw new UserException("User doesn't exists");
        }
        userRepository.deleteById(email);
    }
    
}
