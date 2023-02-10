package com.gymservice.web.workoutAPI;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.userAPI.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findAllByCreator(User user);

    @Query("SELECT w FROM Workout w WHERE privacy IN :privacies OR creator=:user")
    List<Workout> findByPrivacyOrCreator(List<Privacy> privacies, User user);
}
