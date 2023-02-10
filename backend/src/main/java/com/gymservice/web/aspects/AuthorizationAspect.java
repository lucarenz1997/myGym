package com.gymservice.web.aspects;

import com.gymservice.web.errorhandling.ForbiddenException;
import com.gymservice.web.userAPI.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    @Before("@annotation(com.gymservice.web.annotations.Authorized)")
    public void authorize(JoinPoint joinPoint) {
        System.out.println(joinPoint);
        Object[] args = joinPoint.getArgs();
        User authenticatedUser = null;
        Long updatedObjectId = null;
        for (Object arg : args) {
            if (arg instanceof User) {
                authenticatedUser = (User) arg;
//                break;
            }
            if(arg instanceof Long){
                updatedObjectId = (Long) arg;
//                break;
            }

        }

        if (authenticatedUser == null) {
            throw new ForbiddenException("User not found");
        }
        boolean isAdmin = !authenticatedUser.getId().equals(updatedObjectId) && authenticatedUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if ( !authenticatedUser.getId().equals(updatedObjectId) && !isAdmin) {
            throw new ForbiddenException("User not authorized to perform this action");
        }
    }
}