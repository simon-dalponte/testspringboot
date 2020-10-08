package com.example.demo.mapper;

import com.example.demo.dto.GameDTO;
import com.example.demo.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface GameMapper extends GenericMapper<Game, GameDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Game asEntity(GameDTO dto);
}