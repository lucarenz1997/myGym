package com.gymservice.web.exerciseAPI;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.muscleGroupAPI.MuscleGroup;
import com.gymservice.web.userAPI.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long > {


    @Query("SELECT e FROM Exercise e WHERE muscleGroup IN :muscleGroup AND privacy!=:privacy OR muscleGroup IN :muscleGroup AND creator=:user")
    List<Exercise> findByMuscleGroupOrCreator(Set<MuscleGroup> muscleGroup, User user, Privacy privacy);

    List<Exercise> findByNameContaining(String name);


    @Query("SELECT e FROM Exercise e WHERE muscleGroup IN :muscleGroup")
    List<Exercise> findByMuscleGroup(Set<MuscleGroup> muscleGroup);

    @Query("SELECT e FROM Exercise e WHERE privacy IN :privacies")
    List<Exercise> findByPrivacy(@Param("privacies") Set<Privacy> privacies);

    List<Exercise> findByCreator(User user);
    @Query("SELECT e FROM Exercise e WHERE privacy IN :privacies OR creator=:user")
    List<Exercise> findByPrivacyOrCreator(List<Privacy> privacies, User user);

}
