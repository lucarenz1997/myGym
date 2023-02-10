package com.gymservice.web.setAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateGymSetDto {
    @NotNull
    int repetitions;
    @NotNull
    double weight;
    String comment;
}
