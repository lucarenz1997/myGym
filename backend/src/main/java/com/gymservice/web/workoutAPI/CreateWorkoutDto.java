package com.gymservice.web.workoutAPI;

import com.gymservice.web.enums.Privacy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateWorkoutDto {
    @Size(min = 4, max = 20, message = "Must be between 4 and 20 characters")
//    @Null(groups = CreateWorkoutDto.class)
    String name = "My Workout";

    @Enumerated(EnumType.STRING)
    Privacy privacy = Privacy.PUBLIC;

    Set<Long> exercises;
}
