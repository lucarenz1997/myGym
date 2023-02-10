package com.gymservice.web.userAPI;

import com.gymservice.security.entity.Authorities;
import com.gymservice.security.repositories.AuthoritiesRepository;
import com.gymservice.web.mappers.UserMapper;
import com.gymservice.web.annotations.Authorized;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Configuration
public class UserService {

    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserService(UserRepository userRepository, AuthoritiesRepository authoritiesRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    public UserDto saveUser(CreateUserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userMapper.toUser(user);
        UserDto userDto = userMapper.toUserDto(userRepository.save(newUser));
        Authorities authorities = new Authorities();
        authorities.setUser(newUser);
        authorities.setAuthority("ROLE_USER");
        authoritiesRepository.save(authorities);
        return userDto;
    }



    @Authorized
    public UserDto updateUser(CreateUserDto user, Long id, User authenticatedUser) {
            User updatedUser = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

            if(user.getEmail() != null){
                updatedUser.setEmail(user.getEmail());
            }
            if(user.getUsername() != null){
                updatedUser.setUsername(user.getUsername());
            }
            if(user.getPassword() != null){
                updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userMapper.toUserDto(userRepository.save(updatedUser));

    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted";
    }
}
