package com.gymservice.web.utils;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.exerciseAPI.Exercise;
import com.gymservice.web.userAPI.User;
import com.gymservice.web.workoutAPI.Workout;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public boolean userHasRoleAdmin(User user) {
        return user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
    public static boolean isAuthorized(User user, Exercise exercise) {
        boolean isAdmin = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return exercise.getPrivacy().equals(Privacy.PUBLIC) || exercise.getPrivacy().equals(Privacy.BASE) || (exercise.getPrivacy().equals(Privacy.PRIVATE) && exercise.getCreator().getId().equals(user.getId()) || isAdmin);
    }

    // FIXME
    public static boolean isAuthorized(User user, Workout workout) {
        boolean isAdmin = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return workout.getPrivacy().equals(Privacy.PUBLIC) || workout.getPrivacy().equals(Privacy.BASE) || (workout.getPrivacy().equals(Privacy.PRIVATE) && workout.getCreator().getId().equals(user.getId()) || isAdmin);
    }

}
