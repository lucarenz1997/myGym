package com.gymservice.web.userAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

//    @Query("SELECT u from User u left join fetch u.authorities where u.username = :username")
    User findByUsername(String username);
}
