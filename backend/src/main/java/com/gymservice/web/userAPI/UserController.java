package com.gymservice.web.userAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) { return ResponseEntity.ok(userService.deleteUser(id));}

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser( @Valid @RequestBody CreateUserDto user){
        return ResponseEntity.ok(userService.saveUser(user));}


    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody CreateUserDto user, @PathVariable Long id, @AuthenticationPrincipal User authenticatedUser){
            return ResponseEntity.ok(userService.updateUser(user, id, authenticatedUser));
    }
}
