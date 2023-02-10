package com.gymservice.web.setAPI;

import com.fasterxml.jackson.annotation.*;
import com.gymservice.web.exerciseAPI.Exercise;
import com.gymservice.web.userAPI.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gym_set")
public class GymSet {
//    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int repetitions;

    @NotNull
    private double weight;

    @Size(max = 250)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "exercise")
    private Exercise exercise;




}
