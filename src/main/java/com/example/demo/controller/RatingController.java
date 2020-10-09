package com.example.demo.controller;

import com.example.demo.dto.RatingDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Rating API")
public interface RatingController {
    @ApiOperation("Add new data")
    public RatingDTO save(@RequestBody RatingDTO rating);

    @ApiOperation("Find by Id")
    public RatingDTO findById(@PathVariable("id") String id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") String id);

    @ApiOperation("Find all data")
    public List<RatingDTO> list();

    @ApiOperation("Pagination request")
    public Page<RatingDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public RatingDTO update(@RequestBody RatingDTO dto, @PathVariable("id") String id);
}