package com.gymservice.web.setAPI;


import com.gymservice.web.exerciseAPI.Exercise;
import com.gymservice.web.userAPI.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymSetRepository extends JpaRepository<GymSet, Long > {
    List<GymSet> findByExerciseAndUser(Exercise exercise, User user);
}
