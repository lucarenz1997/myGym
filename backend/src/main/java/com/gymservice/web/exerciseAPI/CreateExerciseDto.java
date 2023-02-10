package com.gymservice.web.exerciseAPI;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.muscleGroupAPI.MuscleGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateExerciseDto {
    @Size(min = 4, max = 20, message = "Must be between 4 and 20 characters")
    @Null(groups = CreateExerciseDto.class)
    private String name;
    @Size(max = 50)
    @Null(groups = CreateExerciseDto.class)
    private String description;

    private Privacy privacy=Privacy.PUBLIC;

    private MuscleGroup muscleGroup;
}
