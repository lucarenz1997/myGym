package com.gymservice.web.setAPI;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GymSetDto {
    int repetitions;
    double weight;
    String comment;
}
