package com.example.demo.controller.impl;

import com.example.demo.controller.RatingController;
import com.example.demo.dto.RatingDTO;
import com.example.demo.mapper.GameMapper;
import com.example.demo.mapper.RatingMapper;
import com.example.demo.model.Rating;
import com.example.demo.service.GameService;
import com.example.demo.service.RatingService;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/rating")
@RestController
public class RatingControllerImpl implements RatingController {
    @Autowired
    private final RatingService ratingService;
    @Autowired
    private final RatingMapper ratingMapper;

    public RatingControllerImpl(RatingService ratingService, RatingMapper ratingMapper) {
        this.ratingService = ratingService;
        this.ratingMapper = ratingMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RatingDTO save(@RequestBody RatingDTO ratingDTO) {
        Rating rating = ratingMapper.asEntity(ratingDTO);
        rating.setId(Generators.randomBasedGenerator().generate().toString());
        return ratingMapper.asDTO(ratingService.save(rating));
    }

    @Override
    @GetMapping("/{id}")
    public RatingDTO findById(@PathVariable("id") String id) {
        Rating rating = ratingService.findById(id).orElse(null);
        return ratingMapper.asDTO(rating);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        ratingService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<RatingDTO> list() {
        return ratingMapper.asDTOList(ratingService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<RatingDTO> pageQuery(Pageable pageable) {
        Page<Rating> ratingPage = ratingService.findAll(pageable);
        List<RatingDTO> dtoList = ratingPage
                .stream()
                .map(ratingMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, ratingPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public RatingDTO update(@RequestBody RatingDTO ratingDTO, @PathVariable("id") String id) {
        Rating rating = ratingMapper.asEntity(ratingDTO);
        return ratingMapper.asDTO(ratingService.update(rating, id));
    }
}