package com.example.demo.controller.impl;

import com.example.demo.controller.GameController;
import com.example.demo.dto.GameDTO;
import com.example.demo.dto.RatingDTO;
import com.example.demo.mapper.GameMapper;
import com.example.demo.mapper.RatingMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Game;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.service.GameService;
import com.example.demo.service.RatingService;
import com.example.demo.service.UserService;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/game")
@RestController
public class GameControllerImpl implements GameController {

    @Autowired
    private final GameService gameService;
    @Autowired
    private final GameMapper gameMapper;

    @Autowired
    private final RatingMapper ratingMapper;

    @Autowired final RatingService ratingService;

    @Autowired
    private final UserService userService;
    @Autowired
    private final UserMapper userMapper;

    public GameControllerImpl(GameService gameService, GameMapper gameMapper1, RatingMapper ratingMapper, RatingService ratingService, UserService userService, UserMapper userMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper1;
        this.ratingMapper = ratingMapper;
        this.ratingService = ratingService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO save(@RequestBody GameDTO gameDTO) {
        Game game = gameMapper.asEntity(gameDTO);
        game.setId(Generators.randomBasedGenerator().generate().toString());
        return gameMapper.asDTO(gameService.save(game));
    }

    @PostMapping("/{id}/rating/user/{id_user}")
    public RatingDTO addRating(@RequestBody RatingDTO ratingDTO, @PathVariable("id") String id, @PathVariable("id_user") String id_user ) {
        Game game = gameService.findById(id).orElse(null);
        User user = userService.findById(id_user).orElse(null);
        Rating rating = ratingMapper.asEntity(ratingDTO);
        rating.setId(Generators.randomBasedGenerator().generate().toString());
        rating.setUser(user);
        rating.setGame(game);
        return ratingMapper.asDTO(ratingService.save(rating));
    }

    @Override
    @GetMapping("/{id}")
    public GameDTO findById(@PathVariable("id") String id) {
        Game game = gameService.findById(id).orElse(null);
        return gameMapper.asDTO(game);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        gameService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<GameDTO> list() {
        return gameMapper.asDTOList(gameService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<GameDTO> pageQuery(Pageable pageable) {
        Page<Game> gamePage = gameService.findAll(pageable);
        List<GameDTO> dtoList = gamePage
                .stream()
                .map(gameMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, gamePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public GameDTO update(@RequestBody GameDTO gameDTO, @PathVariable("id") String id) {
        Game game = gameMapper.asEntity(gameDTO);
        return gameMapper.asDTO(gameService.update(game, id));
    }
}