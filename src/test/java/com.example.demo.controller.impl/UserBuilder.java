package com.example.demo.controller.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

import java.util.List;

public class UserBuilder {
    private static List<UserDTO> listDTO;
    private static List<User> listEntities;
    private static UserDTO DTO;
    private static User entity;

    public static List<UserDTO> getListDTO() { return listDTO; }
    public static void setListDTO(List<UserDTO> listDTO) { UserBuilder.listDTO = listDTO; }
    public static List<User> getListEntities() { return listEntities; }
    public static void setListEntities(List<User> listEntities) { UserBuilder.listEntities = listEntities; }
    public static UserDTO getDTO() { return DTO; }
    public static void setDTO(UserDTO dto) { UserBuilder.DTO = dto; }
    public static User getEntity() { return entity; }
    public static void setEntity(User entity) { UserBuilder.entity = entity; }
}
