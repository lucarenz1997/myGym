package com.gymservice.security.config.service;

import com.gymservice.web.userAPI.User;
import com.gymservice.web.userAPI.UserRepository;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //  Requires @EnableGlobalMethodSecurity(securedEnabled = true) in securityconfig file
    //        only allows admins to use this service
    @Secured({"ROLE_ADMIN"})
    public List<User> getAllUserAccounts
    () {
        return userRepository.findAll();
    }
}
