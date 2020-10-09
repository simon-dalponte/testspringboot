package com.example.demo.mapper;

import com.example.demo.dto.RatingDTO;
import com.example.demo.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RatingMapper extends GenericMapper<Rating, RatingDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Rating asEntity(RatingDTO dto);
}