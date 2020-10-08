package com.example.demo.mapper;

import com.example.demo.dto.GameDTO;
import com.example.demo.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GameMapper extends GenericMapper<Game, GameDTO> {
    @Override
    @Mapping(target = "id")
    Game asEntity(GameDTO dto);
}