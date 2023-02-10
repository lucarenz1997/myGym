package com.gymservice.web.exerciseAPI;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.muscleGroupAPI.MuscleGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ExerciseDto {
    String id;
    String name;
    String description;
    Privacy privacy;
    MuscleGroup muscleGroup;
//    UserDto creator;
}
