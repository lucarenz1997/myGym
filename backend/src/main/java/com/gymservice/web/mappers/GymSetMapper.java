package com.gymservice.web.mappers;

import com.gymservice.web.setAPI.GymSet;
import com.gymservice.web.setAPI.GymSetDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GymSetMapper {
    GymSetDto toGymSetDto (GymSet gymSet);

    GymSet toGymSet(GymSetDto gymSetDto);
}
