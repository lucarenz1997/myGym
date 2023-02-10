package com.gymservice.web.userAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymservice.web.exerciseAPI.Exercise;
import com.gymservice.web.setAPI.GymSet;
import com.gymservice.web.workoutAPI.Workout;
import com.gymservice.security.entity.Authorities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 20, message = "Must be between 4 and 20 and cannot consist of special characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$")
    @NotBlank
    @NotEmpty
    private String username;

    @Column(name = "email", unique=true)
    @NotNull
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;


    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<GymSet> gymSet;


    @JsonIgnore
    @OneToMany(mappedBy = "creator")
    private Set<Exercise> exercises;

    @JsonIgnore
    @OneToMany(mappedBy = "creator")
    private Set<Workout> workouts;

    @JsonIgnore
    // from lazy to eager
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Authorities> authorities = new HashSet<>();

}
