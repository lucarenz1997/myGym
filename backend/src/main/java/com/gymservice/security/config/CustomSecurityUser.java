package com.gymservice.security.config;


import com.gymservice.web.userAPI.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomSecurityUser extends User implements UserDetails {
    public CustomSecurityUser() {
    }

    public CustomSecurityUser(User user) {
        this.setAuthorities(user.getAuthorities());
        this.setId(user.getId());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result  =1;
        result = prime * result + (((getUsername() == null)) ? 0 : getUsername().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
       if(this == obj)return true;
       if(obj==null)return false;
       if(getClass() != obj.getClass()) return false;
       User other = (User) obj;
       if(getUsername() == null){
           if(other.getUsername() != null)return false;

       } else if (!getUsername().equals(other.getUsername()))
           return false;
       return true;

    }
}
