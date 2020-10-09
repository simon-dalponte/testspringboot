package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper extends GenericMapper<User, UserDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    User asEntity(UserDTO dto);
}