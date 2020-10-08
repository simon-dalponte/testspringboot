package com.example.demo.controller;

import com.example.demo.dto.GameDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Game API")
public interface GameController {
    @ApiOperation("Add new data")
    public GameDTO save(@RequestBody GameDTO game);

    @ApiOperation("Find by Id")
    public GameDTO findById(@PathVariable("id") String id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") String id);

    @ApiOperation("Find all data")
    public List<GameDTO> list();

    @ApiOperation("Pagination request")
    public Page<GameDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public GameDTO update(@RequestBody GameDTO dto, @PathVariable("id") String id);
}