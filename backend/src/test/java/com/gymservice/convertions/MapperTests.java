package com.gymservice.convertions;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.exerciseAPI.Exercise;
import com.gymservice.web.exerciseAPI.ExerciseDetailsDto;
import com.gymservice.web.exerciseAPI.ExerciseDto;
import com.gymservice.web.mappers.*;
import com.gymservice.web.muscleGroupAPI.MuscleGroup;
import com.gymservice.web.setAPI.GymSet;
import com.gymservice.web.setAPI.GymSetDto;
import com.gymservice.web.userAPI.User;
import com.gymservice.web.userAPI.UserDto;
import com.gymservice.web.workoutAPI.Workout;
import com.gymservice.web.workoutAPI.WorkoutDetailsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTests {


    @Autowired
    ExerciseMapperImpl exerciseMapper;


    @Autowired
    UserMapperImpl userMapper;
    @Autowired
    GymSetMapperImpl gymSetMapper;
    @Autowired
    WorkoutMapperImpl workoutMapper;


    @Nested
    class ExerciseMappingTests {

        @Test
        public void Should_Convert_Exercise_To_ExerciseDto() {
            Exercise exercise = new Exercise();
            exercise.setId(1L);
            exercise.setName("Bench Press");
            exercise.setDescription("New Description");
            exercise.setPrivacy(Privacy.PUBLIC);
            exercise.setMuscleGroup(MuscleGroup.CALVES);
            ExerciseDto exerciseDto = exerciseMapper.toExerciseDto(exercise);

            Assertions.assertEquals(exercise.getName(), exerciseDto.getName());
            Assertions.assertEquals(exercise.getPrivacy(), exerciseDto.getPrivacy());
            Assertions.assertEquals(exercise.getDescription(), exerciseDto.getDescription());
            Assertions.assertEquals(exercise.getMuscleGroup(), exerciseDto.getMuscleGroup());
        }

        @Test
        public void Should_Convert_ExerciseDto_To_Exercise() {
            ExerciseDto exerciseDto = new ExerciseDto();
            exerciseDto.setName("Bench Press");
            exerciseDto.setDescription("New Description");
            exerciseDto.setPrivacy(Privacy.PUBLIC);
            exerciseDto.setMuscleGroup(MuscleGroup.CALVES);
            Exercise exercise = exerciseMapper.toExercise(exerciseDto);

            Assertions.assertEquals(exercise.getName(), exerciseDto.getName());
            Assertions.assertEquals(exercise.getPrivacy(), exerciseDto.getPrivacy());
            Assertions.assertEquals(exercise.getDescription(), exerciseDto.getDescription());
            Assertions.assertEquals(exercise.getMuscleGroup(), exerciseDto.getMuscleGroup());
        }

        @Test
        public void Should_Convert_Exercise_To_ExerciseDetails() {
            Exercise exercise = new Exercise();
            exercise.setId(1L);
            exercise.setName("Bench Press");
            exercise.setDescription("New Description");
            exercise.setPrivacy(Privacy.PUBLIC);
            exercise.setMuscleGroup(MuscleGroup.CALVES);
            ExerciseDetailsDto exerciseDetailsDto = exerciseMapper.toExerciseDetailsDto(exercise);

            Assertions.assertEquals(exercise.getName(), exerciseDetailsDto.getName());
            Assertions.assertEquals(exercise.getPrivacy(), exerciseDetailsDto.getPrivacy());
            Assertions.assertEquals(exercise.getDescription(), exerciseDetailsDto.getDescription());
            Assertions.assertEquals(exercise.getMuscleGroup(), exerciseDetailsDto.getMuscleGroup());
        }

    }

    @Nested
    class GymSetMappingTests{
        static User user = new User();

        @BeforeAll
        static void setup(){
            user.setUsername("TestUser");
            user.setPassword("MyP@ssw0rd");
            user.setId(1L);
        }

        @Test
        public void Should_Convert_GymSet_To_GymSetDto(){
            GymSet gymSet = new GymSet();
            gymSet.setWeight(1);
            gymSet.setUser(user);
            gymSet.setComment("I am a comment");
            gymSet.setRepetitions(20);
            gymSet.setExercise(new Exercise());

            GymSetDto gymSetDto = gymSetMapper.toGymSetDto(gymSet);

            Assertions.assertEquals(gymSet.getComment(), gymSetDto.getComment());
            Assertions.assertEquals(gymSet.getWeight(), gymSetDto.getWeight());
            Assertions.assertEquals(gymSet.getRepetitions(), gymSetDto.getRepetitions());
        }

        @Test
        public void Should_Convert_GymSetDto_To_GymSet(){
            GymSetDto gymSetDto = new GymSetDto();
            gymSetDto.setWeight(1);
            gymSetDto.setComment("I am a comment");
            gymSetDto.setRepetitions(20);


            GymSet gymSet = gymSetMapper.toGymSet(gymSetDto);

            Assertions.assertEquals(gymSet.getComment(), gymSetDto.getComment());
            Assertions.assertEquals(gymSet.getWeight(), gymSetDto.getWeight());
            Assertions.assertEquals(gymSet.getRepetitions(), gymSetDto.getRepetitions());
        }

    }

    @Nested
    class UserMappingTests {

        @Test
        public void Should_Convert_User_To_UserDto(){

            User user = new User();
            user.setUsername("TestUser");
            user.setPassword("MyP@ssw0rd");
            user.setId(1L);
            UserDto userDto = userMapper.toUserDto(user);
            Assertions.assertEquals(userDto.getEmail(), user.getEmail());
            Assertions.assertEquals(userDto.getUsername(), user.getUsername());
        }

        @Test
        public void Should_Convert_UserDto_To_User(){
            UserDto userDto = new UserDto();
            userDto.setId(1L);
            userDto.setUsername("MyTesTName");
            User user = userMapper.toUser(userDto);

            Assertions.assertEquals(userDto.getEmail(), user.getEmail());
            Assertions.assertEquals(userDto.getUsername(), user.getUsername());
        }
    }


    @Nested
    class WorkoutMappingTests{

        public void Should_Convert_Workout_To_WorkoutDto(){
            Workout workout = new Workout();
            workout.setName("My workout");
            workout.setCreator(new User());
            workout.setPrivacy(Privacy.PUBLIC);

            WorkoutDetailsDto workoutDetailsDto = workoutMapper.toWorkoutDetailsDto(workout);

            Assertions.assertEquals(workout.getPrivacy(), workoutDetailsDto.getPrivacy());
            Assertions.assertEquals(workout.getName(), workoutDetailsDto.getName());
        }
    }
}
