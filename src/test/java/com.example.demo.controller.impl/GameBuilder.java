package com.example.demo.controller.impl;

import com.example.demo.dto.GameDTO;
import com.example.demo.model.Game;

import java.util.List;

public class GameBuilder {
    private static List<GameDTO> listDTO;
    private static List<Game> listEntities;
    private static GameDTO DTO;
    private static Game entity;

    public static List<GameDTO> getListDTO() {
        return listDTO;
    }

    public static void setListDTO(List<GameDTO> listDTO1) {
        listDTO = listDTO1;
    }

    public static List<Game> getListEntities() {
        return listEntities;
    }

    public static void setListEntities(List<Game> listEntities1) {
        listEntities = listEntities1;
    }


    public static GameDTO getDTO() {
        return DTO;
    }

    public static void setDTO(GameDTO dto) {
        DTO = dto;
    }

    public static Game getEntity() {
        return entity;
    }

    public static void setEntity(Game game) {
        entity = game;
    }
}
