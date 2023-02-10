package com.gymservice.web.workoutAPI;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.errorhandling.ForbiddenException;
import com.gymservice.web.exerciseAPI.Exercise;
import com.gymservice.web.exerciseAPI.ExerciseRepository;
import com.gymservice.web.mappers.WorkoutMapper;
import com.gymservice.web.userAPI.User;
import com.gymservice.web.utils.AuthorizationService;
import com.gymservice.web.annotations.Authorized;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.gymservice.web.utils.AuthorizationService.isAuthorized;

@Service
public class WorkoutService {


    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final AuthorizationService authorizationService;
    private final ExerciseRepository exerciseRepository;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper,
                          AuthorizationService authorizationService,
                          ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.authorizationService = authorizationService;
        this.exerciseRepository = exerciseRepository;
    }

    public List<WorkoutDetailsDto> getWorkouts(User user) {
        if (authorizationService.userHasRoleAdmin(user)) {
            return workoutRepository.findAll().stream()
                    .map(workoutMapper::toWorkoutDetailsDto).collect(Collectors.toList());
        } else {
            return workoutRepository.findByPrivacyOrCreator(Arrays.asList(Privacy.BASE, Privacy.PUBLIC), user).stream()
                    // FIXME
                    .filter(workout -> isAuthorized(user, workout))
                    .map(workoutMapper::toWorkoutDetailsDto).collect(Collectors.toList());
        }
    }

    // TODO empty workout causes "ids must not be null"
    public WorkoutDetailsDto saveWorkout(CreateWorkoutDto workout, User user) {
        List<Exercise> exercises = exerciseRepository.findAllById(workout.getExercises());
        // TODO use mapper
        Workout savedWorkout = new Workout();
        savedWorkout.setCreator(user);
        savedWorkout.setPrivacy(workout.getPrivacy());
        savedWorkout.setName(workout.getName());
        savedWorkout.setExercises(new HashSet<>(exercises));
        return workoutMapper.toWorkoutDetailsDto(workoutRepository.save(savedWorkout));
    }

    @Authorized
    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }


    //TODO allow to order the exercises. should always return in same order (as defined by user)
    @Authorized
    public WorkoutDetailsDto updateWorkout(CreateWorkoutDto workout, Long id, User user) {
        Workout updatedWorkout = workoutRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<Exercise> exercises = exerciseRepository.findAllById(workout.getExercises());
        if(workout.getExercises() != null){
            updatedWorkout.setExercises(new HashSet<>(exercises));
        }
        if(workout.getPrivacy() != null){
            updatedWorkout.setPrivacy(workout.getPrivacy());
        }
        if(workout.getName() != null){
            updatedWorkout.setName(workout.getName());
        }

        return workoutMapper.toWorkoutDetailsDto(workoutRepository.save(updatedWorkout));
    }


    public WorkoutDetailsDto getWorkout(Long id, User user) {
        Workout workout = workoutRepository.findById(id).orElseThrow(NoSuchElementException::new);

         // TODO refactor this method
        boolean isAdmin = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (workout.getPrivacy().equals(Privacy.PUBLIC) || workout.getPrivacy().equals(Privacy.BASE) || workout.getCreator().getId().equals(user.getId()) || isAdmin ) {
            return workoutMapper.toWorkoutDetailsDto(workout);
        }else {
            throw new ForbiddenException("You do not have access to this Workout");
        }

    }

    public List<WorkoutDetailsDto> getMyWorkouts(User user) {
        return workoutRepository.findAllByCreator(user).stream().map(workoutMapper::toWorkoutDetailsDto).collect(Collectors.toList());
    }
}
