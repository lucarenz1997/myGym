package com.gymservice.security.Controller;


import com.gymservice.security.config.service.AdminService;
import com.gymservice.web.userAPI.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingsController {

    private final AdminService adminService;

    public GreetingsController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAdminStuff(@AuthenticationPrincipal User user){
        List<User> allUsers = adminService.getAllUserAccounts();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping()
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from our API");
    }

    @GetMapping("/say-good-bye")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok("Goodbye");
    }
}
