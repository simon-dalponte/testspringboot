package com.example.demo.controller.impl;

import com.example.demo.dto.RatingDTO;
import com.example.demo.model.Rating;

import java.util.List;

public class RatingBuilder {
    private static Rating entity;
    private static RatingDTO DTO;
    private static List<Rating> listEntities;
    private static List<RatingDTO> listDTO;

    public static List<RatingDTO> getListDTO() { return listDTO; }
    public static void setListDTO(List<RatingDTO> listDTO) { RatingBuilder.listDTO = listDTO; }
    public static List<Rating> getListEntities() { return listEntities; }
    public static void setListEntities(List<Rating> listEntities) { RatingBuilder.listEntities = listEntities; }
    public static RatingDTO getDTO() { return DTO; }
    public static void setDTO(RatingDTO dto) { RatingBuilder.DTO = dto; }
    public static Rating getEntity() { return entity; }
    public static void setEntity(Rating entity) { RatingBuilder.entity = entity; }
}
