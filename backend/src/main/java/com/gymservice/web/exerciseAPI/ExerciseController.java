package com.gymservice.web.exerciseAPI;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.muscleGroupAPI.MuscleGroup;
import com.gymservice.web.userAPI.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/v1")
public class ExerciseController {
    private final ExerciseService exerciseService;
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseDto>> getExercises(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(exerciseService.getAllExercises(user));
    }

    @GetMapping(value = "/exercises", params = "muscleGroup")
    public ResponseEntity<List<ExerciseDto>> getExercisesWithMuscleGroup(@RequestParam Set<MuscleGroup> muscleGroup, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(exerciseService.getExercisesByMuscleGroup(muscleGroup, user));
    }
    @GetMapping(value = "/exercises", params = "privacies")
    public ResponseEntity<List<ExerciseDto>> getExercisesByPrivacies(@RequestParam Set<Privacy> privacies, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(exerciseService.getExercisesByPrivacies(privacies, user));
    }

    @GetMapping(value = "/exercises", params = "name")
    public ResponseEntity<List<ExerciseDto>> getExercisesByName(@RequestParam String name, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(exerciseService.getExercisesByName(name, user));
    }

    @GetMapping(value = "/my-exercises")
    public ResponseEntity<List<ExerciseDto>> getMyExercises(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(exerciseService.getExercisesByCreator(user));
    }

    // FIXME return only the sets where you are creator
    @GetMapping("/exercise/{id}")
    public ResponseEntity<ExerciseDetailsDto> getSpecificExercise(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok((exerciseService.getExercise(id, user)));
    }

    @PutMapping("/exercise/{id}")
    public ResponseEntity<ExerciseDto> updateExercise(@PathVariable Long id,  @Valid @RequestBody CreateExerciseDto exercise, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(exerciseService.updateExercise(id, exercise, user));
    }

    // FIXME allow deletion??
    @DeleteMapping("/exercise/{id}")
    public void deleteExercise(@PathVariable Long id, @AuthenticationPrincipal User user){ exerciseService.deleteExercise(id, user);}

    @PostMapping("/exercise")
    public ResponseEntity<ExerciseDto> saveNewExercise(@Valid @RequestBody Exercise exercise, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(exerciseService.saveExercise(exercise, user));
    }

}
