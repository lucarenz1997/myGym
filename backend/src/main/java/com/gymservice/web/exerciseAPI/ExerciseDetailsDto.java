package com.gymservice.web.exerciseAPI;


import com.gymservice.web.enums.Privacy;
import com.gymservice.web.muscleGroupAPI.MuscleGroup;
import com.gymservice.web.setAPI.GymSetDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ExerciseDetailsDto {
    String id;
    String name;
    String description;
    Privacy privacy;
    MuscleGroup muscleGroup;
    List<GymSetDto> sets;
}
