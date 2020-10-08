package com.example.demo.controller.impl;

import com.example.demo.controller.GameController;
import com.example.demo.dto.GameDTO;
import com.example.demo.mapper.GameMapper;
import com.example.demo.model.Game;
import com.example.demo.service.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/game")
@RestController
public class GameControllerImpl implements GameController {
    private final GameService gameService;
    private final GameMapper gameMapper;

    public GameControllerImpl(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
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
    public GameDTO findById(@PathVariable("id") Long id) {
        Game game = gameService.findById(id).orElse(null);
        return gameMapper.asDTO(game);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
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
    public GameDTO update(@RequestBody GameDTO gameDTO, @PathVariable("id") Long id) {
        Game game = gameMapper.asEntity(gameDTO);
        return gameMapper.asDTO(gameService.update(game, id));
    }
}