package com.example.demo.controller.impl;

import com.example.demo.controller.GameController;
import com.example.demo.dto.GameDTO;
import com.example.demo.mapper.GameMapper;
import com.example.demo.model.Game;
import com.example.demo.service.GameService;
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

    public GameControllerImpl(GameService gameService, GameMapper gameMapper1) {
        this.gameService = gameService;
        this.gameMapper = gameMapper1;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO save(@RequestBody GameDTO gameDTO) {
        Game game = gameMapper.asEntity(gameDTO);
        return gameMapper.asDTO(gameService.save(game));
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