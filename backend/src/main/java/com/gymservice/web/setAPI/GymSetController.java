package com.gymservice.web.setAPI;

import com.gymservice.web.userAPI.User;
import com.gymservice.web.annotations.Authorized;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class GymSetController {

   private final GymSetService gymSetService;

    public GymSetController(GymSetService gymSetService) {
        this.gymSetService = gymSetService;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/sets")
    public ResponseEntity<List<GymSetDto>> getAllGymSets() {
        return ResponseEntity.ok(gymSetService.getGymSets());
    }


    @GetMapping(value = "/sets/{exerciseId}")
    public ResponseEntity<List<GymSetDto>> getAllGymSetsByUserAndExercise(@AuthenticationPrincipal User user, @PathVariable Long exerciseId) {
        return ResponseEntity.ok(gymSetService.getGymSets(user, exerciseId));
    }

    @PostMapping("/set/{exerciseId}")
    public ResponseEntity<GymSetDto> saveGymSet(@PathVariable Long exerciseId, @Valid @RequestBody CreateGymSetDto gymSet, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(gymSetService.saveGymSet(exerciseId, gymSet, user));
    }
    @Authorized
    @PutMapping("/set/{id}")
    public ResponseEntity<GymSetDto> updateGymSet(@RequestBody CreateGymSetDto gymSet, @PathVariable Long id, @AuthenticationPrincipal User user) { return ResponseEntity.ok(gymSetService.updateGymSet(gymSet, id));}

    @Authorized
    @DeleteMapping("/sets/{id}")
    public void deleteGymSet(@PathVariable Long id, @AuthenticationPrincipal User user){
       gymSetService.deleteGymSet(id);
    }

}
