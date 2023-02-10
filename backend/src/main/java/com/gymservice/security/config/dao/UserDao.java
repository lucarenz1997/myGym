package com.gymservice.security.config.dao;

import com.gymservice.security.config.CustomSecurityUser;
import com.gymservice.web.userAPI.User;
import com.gymservice.web.userAPI.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

   UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails findUserByUsername(String username){
        System.out.println("I AM HERE      " +  username);
       User user = userRepository.findByUsername(username);
        System.out.println("I AM STILL HERE");
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("Username and/or password was incorrect.");
        }
        return new CustomSecurityUser(user);
   }
}
