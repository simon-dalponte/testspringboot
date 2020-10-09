package com.example.demo.controller.impl;

import com.example.demo.controller.UserController;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/user")
@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final UserMapper userMapper;

    public UserControllerImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody UserDTO userDTO) {
        User user = userMapper.asEntity(userDTO);
        return userMapper.asDTO(userService.save(user));
    }

    @Override
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") String id) {
        User user = userService.findById(id).orElse(null);
        return userMapper.asDTO(user);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        userService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<UserDTO> list() {
        return userMapper.asDTOList(userService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<UserDTO> pageQuery(Pageable pageable) {
        Page<User> userPage = userService.findAll(pageable);
        List<UserDTO> dtoList = userPage
                .stream()
                .map(userMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, userPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO, @PathVariable("id") String id) {
        User user = userMapper.asEntity(userDTO);
        return userMapper.asDTO(userService.update(user, id));
    }

}