package com.gymservice.web.exerciseAPI;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymservice.web.enums.Privacy;
import com.gymservice.web.setAPI.GymSet;
import com.gymservice.web.userAPI.User;
import com.gymservice.web.workoutAPI.Workout;
import com.gymservice.web.muscleGroupAPI.MuscleGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="exercise")
public class Exercise  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 20, message = "Must be between 4 and 20 characters")
    @NotBlank
    @NotEmpty
    private String name;

    @Size(max = 50)
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "exercises", cascade = CascadeType.REMOVE)
    private Set<Workout> workouts;

    @Enumerated(EnumType.STRING)
    @NotNull
    private MuscleGroup muscleGroup;

    @Enumerated(EnumType.STRING)
    private Privacy privacy = Privacy.PUBLIC;


    @OneToMany(mappedBy = "exercise", cascade = CascadeType.REMOVE)
    private Set<GymSet> sets;

    @ManyToOne()
    @JoinColumn(name = "creator")
    private User creator;

}
