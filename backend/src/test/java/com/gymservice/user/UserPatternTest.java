package com.gymservice.user;

import com.gymservice.web.userAPI.User;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserPatternTest {

    @Test
    public void testUsernameRegex() {
        User user = new User();
        user.setUsername("validusername");
        String userRegex = "^[a-zA-Z0-9_.-]*$";
        assertTrue(user.getUsername().matches(userRegex));

        user.setUsername("invalid#username");
        assertFalse(user.getUsername().matches(userRegex));

        user.setUsername("invalid username");
        assertFalse(user.getUsername().matches(userRegex));
    }

    @Test
    public void testEmailRegex() {
        User user = new User();
        user.setEmail("valid@email.com");
        String mailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        assertTrue(user.getEmail().matches(mailRegex));

        user.setEmail("invalidemail");
        assertFalse(user.getEmail().matches(mailRegex));
    }

    @Test
    public void testPasswordRegex() {
        User user = new User();
        user.setPassword("ValidPass1@");
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        assertTrue(user.getPassword().matches(passwordRegex));

        user.setPassword("short");
        assertFalse(user.getPassword().matches(passwordRegex));

        user.setPassword("*9s2L5i*L");
        assertTrue(user.getPassword().matches(passwordRegex));

        user.setPassword("*9s2L5i*L");
        assertTrue(user.getPassword().matches(passwordRegex));

        user.setPassword("NoSpecialChar");
        assertFalse(user.getPassword().matches(passwordRegex));
    }
}
