package com.gymservice.web.workoutAPI;

import com.gymservice.web.annotations.Authorized;
import com.gymservice.web.userAPI.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WorkoutController {

    private final WorkoutService workoutService;


    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
          }


    @GetMapping("/workouts")
    public ResponseEntity<List<WorkoutDetailsDto>> getAllWorkouts(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(workoutService.getWorkouts(user));
    }


    @GetMapping("/workout/{id}")
    public ResponseEntity<WorkoutDetailsDto> getWorkout(@PathVariable Long id, @AuthenticationPrincipal User user){
          return ResponseEntity.ok(workoutService.getWorkout(id, user));
    }

//     MY-WORKOUTS
    @GetMapping("/my-workouts")
    public ResponseEntity<List<WorkoutDetailsDto>> getMyWorkouts(@AuthenticationPrincipal User user){
            return ResponseEntity.ok(workoutService.getMyWorkouts(user));
    }

    @PostMapping("/workout")
    public ResponseEntity<WorkoutDetailsDto> saveNewWorkout(@Valid @RequestBody CreateWorkoutDto workout, @AuthenticationPrincipal User user){ return ResponseEntity.ok(workoutService.saveWorkout(workout, user));}


    @PutMapping("workout/{id}")
    public ResponseEntity<WorkoutDetailsDto> updateWorkout(@Valid @RequestBody CreateWorkoutDto workout, @PathVariable Long id, @AuthenticationPrincipal User user) { return ResponseEntity.ok(workoutService.updateWorkout(workout, id, user));}


    @DeleteMapping("/workout/{id}")
    public void deleteWorkout(@PathVariable Long id, @AuthenticationPrincipal User user){
        workoutService.deleteWorkout(id);
    }
}

