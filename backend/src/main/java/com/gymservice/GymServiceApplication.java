package com.gymservice;

import com.gymservice.web.mappers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.gymservice")
public class GymServiceApplication {


	@Bean
	ExerciseMapper  exerciseMapper() { return new ExerciseMapperImpl();}

	@Bean
	UserMapper userMapper() { return new UserMapperImpl();}

	@Bean
	GymSetMapper gymSetMapper() { return new GymSetMapperImpl();}

	@Bean
	WorkoutMapper workoutMapper() { return new WorkoutMapperImpl();}
	public static void main(String[] args) {
		SpringApplication.run(GymServiceApplication.class, args);
	}



}
