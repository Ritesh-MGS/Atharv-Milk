package com.tharv.milk.mapper;

import com.tharv.milk.domain.Collection;
import com.tharv.milk.dto.CollectionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // This makes MapStruct generate a Spring Bean
public interface CollectionMapper {

    // Mapper instance
    CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);

    // Mapping method: Convert Collection entity to CollectionDTO
    // Mapping collection date
    CollectionDTO collectionToCollectionDTO(Collection collection);

    // Mapping method: Convert CollectionDTO to Collection entity
  // Mapping collection date back
    Collection collectionDTOToCollection(CollectionDTO collectionDTO);
}