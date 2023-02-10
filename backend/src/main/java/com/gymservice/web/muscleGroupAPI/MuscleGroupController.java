package com.gymservice.web.muscleGroupAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/api/v1/static")
public class MuscleGroupController {

    @GetMapping("/muscleGroups")
    public List<MuscleGroup> getAllMuscleGroups(){
        return Arrays.stream(MuscleGroup.values()).toList();
    }
}
