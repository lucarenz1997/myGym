package com.gymservice.web.mappers;


import com.gymservice.web.workoutAPI.Workout;
import com.gymservice.web.workoutAPI.WorkoutDetailsDto;
import org.mapstruct.Mapper;


@Mapper(uses = WorkoutMapper.class)
public interface WorkoutMapper {
  WorkoutDetailsDto toWorkoutDetailsDto(Workout workout);
  Workout toWorkout(WorkoutDetailsDto workoutDetailsDto);
}
