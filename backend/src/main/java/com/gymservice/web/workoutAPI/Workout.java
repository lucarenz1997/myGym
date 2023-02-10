package com.gymservice.web.workoutAPI;


import com.gymservice.web.enums.Privacy;
import com.gymservice.web.exerciseAPI.Exercise;
import com.gymservice.web.userAPI.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @javax.validation.constraints.NotNull
    @Size(min = 4, max = 20, message = "Must be between 4 and 20 characters")
    @NotBlank
    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    private Privacy privacy = Privacy.PUBLIC;

    @ManyToMany()
    @JoinTable(name = "workout_exercise",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns =  @JoinColumn(name = "exercise_id")
    )
    private Set<Exercise> exercises;

    @ManyToOne()
    @JoinColumn(name = "workout_creator")
    private User creator;

}
