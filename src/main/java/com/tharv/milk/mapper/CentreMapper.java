package com.tharv.milk.mapper;

import com.tharv.milk.domain.Centre;
import com.tharv.milk.dto.CentreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // This makes MapStruct generate a Spring Bean
public interface CentreMapper {

    // Mapper instance
    CentreMapper INSTANCE = Mappers.getMapper(CentreMapper.class);

    // Mapping method: Convert Centre entity to CentreDTO


    CentreDTO centreToCentreDTO(Centre centre);

    // Mapping method: Convert CentreDTO to Centre entity
    Centre centreDTOToCentre(CentreDTO centreDTO);
}