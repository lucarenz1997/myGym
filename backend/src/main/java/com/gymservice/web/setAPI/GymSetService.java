package com.gymservice.web.setAPI;

import com.gymservice.web.annotations.Authorized;
import com.gymservice.web.exerciseAPI.Exercise;
import com.gymservice.web.exerciseAPI.ExerciseRepository;
import com.gymservice.web.mappers.GymSetMapper;
import com.gymservice.web.userAPI.User;
import com.gymservice.web.userAPI.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class GymSetService {

    private final GymSetRepository gymSetRepository;
    private final ExerciseRepository exerciseRepository;
    private final GymSetMapper gymSetMapper;


    public GymSetService(GymSetRepository gymSetRepository, ExerciseRepository exerciseRepository, GymSetMapper gymSetMapper) {
        this.gymSetRepository = gymSetRepository;
        this.exerciseRepository = exerciseRepository;
        this.gymSetMapper = gymSetMapper;
    }


    public List<GymSetDto> getGymSets() {
        return gymSetRepository.findAll().stream().map(gymSetMapper::toGymSetDto).collect(Collectors.toList());
    }

    public List<GymSetDto> getGymSets(User user, Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NoSuchElementException::new);
        return gymSetRepository.findByExerciseAndUser(exercise, user).stream().map(gymSetMapper::toGymSetDto).collect(Collectors.toList());
    }

    public GymSetDto saveGymSet(Long exerciseId, CreateGymSetDto gymSet, User user) {
        GymSet savedGymSet = new GymSet();
        savedGymSet.setUser(user);
        savedGymSet.setComment(gymSet.getComment());
        savedGymSet.setWeight(gymSet.getWeight());
        savedGymSet.setRepetitions(gymSet.getRepetitions());
        savedGymSet.setExercise(exerciseRepository.findById(exerciseId).orElseThrow(NoSuchElementException::new));
        return gymSetMapper.toGymSetDto(gymSetRepository.save(savedGymSet));
    }


    public GymSetDto updateGymSet(CreateGymSetDto gymSet, Long id) {
        GymSet updatedGymSet = gymSetRepository.findById(id).orElseThrow(NoSuchElementException::new);
        updatedGymSet.setRepetitions(gymSet.getRepetitions());
        updatedGymSet.setComment(gymSet.getComment());
        updatedGymSet.setWeight(gymSet.getWeight());
        return gymSetMapper.toGymSetDto(gymSetRepository.save(updatedGymSet));
    }

    public void deleteGymSet(Long id) {
        gymSetRepository.deleteById(id);
    }
}

