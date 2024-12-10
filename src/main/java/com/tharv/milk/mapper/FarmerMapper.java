package com.tharv.milk.mapper;

import com.tharv.milk.domain.Farmer;
import com.tharv.milk.dto.FarmerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // This makes MapStruct generate a Spring Bean
public interface FarmerMapper {

    // Mapper instance
    FarmerMapper INSTANCE = Mappers.getMapper(FarmerMapper.class);

    // Mapping method: Convert Farmer entity to FarmerDTO
    // Example of a custom mapping
    FarmerDTO farmerToFarmerDTO(Farmer farmer);

    // Mapping method: Convert FarmerDTO to Farmer entity

    Farmer farmerDTOToFarmer(FarmerDTO farmerDTO);
}