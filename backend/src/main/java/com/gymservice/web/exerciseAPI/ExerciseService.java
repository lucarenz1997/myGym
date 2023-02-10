package com.gymservice.web.exerciseAPI;

import com.gymservice.web.enums.Privacy;
import com.gymservice.web.errorhandling.ForbiddenException;
import com.gymservice.web.mappers.ExerciseMapper;
import com.gymservice.web.mappers.GymSetMapper;
import com.gymservice.web.muscleGroupAPI.MuscleGroup;
import com.gymservice.web.setAPI.GymSet;
import com.gymservice.web.userAPI.User;
import com.gymservice.web.utils.AuthorizationService;
import com.gymservice.web.annotations.Authorized;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.gymservice.web.utils.AuthorizationService.isAuthorized;

@Service
public class ExerciseService {


    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;
    private final List<Privacy> authorizedPrivacies = Arrays.asList(Privacy.BASE, Privacy.PUBLIC);
    private final AuthorizationService authorizationService;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper, AuthorizationService authorizationService) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
        this.authorizationService = authorizationService;
    }


    public List<ExerciseDto> getAllExercises(User user) {
        if (authorizationService.userHasRoleAdmin(user)) {
            return exerciseRepository.findAll().stream()
                    .map(exerciseMapper::toExerciseDto).collect(Collectors.toList());
        } else {
            return exerciseRepository.findByPrivacyOrCreator(authorizedPrivacies, user).stream()
                    .filter(exercise -> isAuthorized(user, exercise))
                    .map(exerciseMapper::toExerciseDto).collect(Collectors.toList());
        }
    }


    public List<ExerciseDto> getExercisesByName(String name, User user) {
        return exerciseRepository.findByNameContaining(name).stream()
                .filter(exercise -> isAuthorized(user, exercise))
                .map(exerciseMapper::toExerciseDto)
                .collect(Collectors.toList());
    }


    public List<ExerciseDto> getExercisesByPrivacies(Set<Privacy> privacies, User user) {
        return exerciseRepository.findByPrivacy(privacies).stream()
                // FIXME
                .filter(exercise -> isAuthorized(user, exercise))
                .map(exerciseMapper::toExerciseDto).collect(Collectors.toList());
    }

    public ExerciseDto saveExercise(Exercise exercise, User user) {
        exercise.setCreator(user);
        return exerciseMapper.toExerciseDto(exerciseRepository.save((exercise)));
    }

    public ExerciseDetailsDto getExercise(Long id, User user) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Set<GymSet> gymSets = exercise.getSets().stream().filter(gymSet -> Objects.equals(gymSet.getUser().getId(), user.getId()))
                .sorted(Comparator.comparing(GymSet::getId))
                        .collect(Collectors.toCollection(LinkedHashSet::new));
        exercise.setSets(gymSets);
        if (exercise.getPrivacy().equals(Privacy.PUBLIC) || exercise.getPrivacy().equals(Privacy.BASE) || exercise.getCreator().getId().equals(user.getId())) {
            return exerciseMapper.toExerciseDetailsDto(exercise);
        } else {
            throw new ForbiddenException("You do not have access to this Exercise");
        }
    }

    @Authorized
    public ExerciseDto updateExercise(Long id, CreateExerciseDto exercise, User user) {
        Exercise updatedExercise = exerciseRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (exercise.getDescription() != null) {
            updatedExercise.setDescription(exercise.getDescription());
        }

        if (exercise.getName() != null) {
            updatedExercise.setName(exercise.getName());
        }

        if (exercise.getMuscleGroup() != null) {
            updatedExercise.setMuscleGroup(exercise.getMuscleGroup());
        }

        if (exercise.getPrivacy() != null) {
            updatedExercise.setPrivacy(exercise.getPrivacy());
        }

        return exerciseMapper.toExerciseDto(exerciseRepository.save(updatedExercise));
    }

    @Authorized
    public void deleteExercise(Long id, User user) {
        exerciseRepository.deleteById(id);
    }

    public List<ExerciseDto> getExercisesByCreator(User user) {
        return exerciseRepository.findByCreator(user).stream().map(exerciseMapper::toExerciseDto).collect(Collectors.toList());
    }



    public List<ExerciseDto> getExercisesByMuscleGroup(Set<MuscleGroup> muscleGroup, User user) {
        return exerciseRepository.findByMuscleGroupOrCreator(muscleGroup, user, Privacy.PRIVATE).stream()
                .map(exerciseMapper::toExerciseDto).collect(Collectors.toList());
    }


}

