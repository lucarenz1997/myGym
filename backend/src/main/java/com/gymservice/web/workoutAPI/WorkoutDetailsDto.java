package com.gymservice.web.workoutAPI;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.exerciseAPI.ExerciseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class WorkoutDetailsDto {
    private Long id;
    private String name;
    private Privacy privacy;
    Set<ExerciseDto> exercises;
}
