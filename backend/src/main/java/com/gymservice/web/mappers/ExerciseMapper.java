package com.gymservice.web.mappers;


import com.gymservice.web.exerciseAPI.Exercise;
import com.gymservice.web.exerciseAPI.ExerciseDetailsDto;
import com.gymservice.web.exerciseAPI.ExerciseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses = UserMapper.class)
public interface ExerciseMapper {
    // redundant
    ExerciseMapper MAPPER = Mappers.getMapper(ExerciseMapper.class);

    @Mapping(source = "muscleGroup", target = "muscleGroup")
    ExerciseDto toExerciseDto(Exercise exercise);


    Exercise toExercise(ExerciseDto exerciseDto);

    ExerciseDetailsDto toExerciseDetailsDto(Exercise exercise);


}
