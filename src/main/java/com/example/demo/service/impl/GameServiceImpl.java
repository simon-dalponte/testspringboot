package com.example.demo.service.impl;

import com.example.demo.dao.GameRepository;
import com.example.demo.dto.GameDTO;
import com.example.demo.mapper.GameMapper;
import com.example.demo.model.Game;
import com.example.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    private final GameRepository repository;

    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game save(Game entity) {
        return repository.save(entity);
    }

    @Override
    public List<Game> save(List<Game> entities) {
        return (List<Game>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Game> findById(String id) {
        return repository.findById(id);
    }


    @Override
    public List<Game> findAll() {
        return (List<Game>) repository.findAll();
    }

    @Override
    public Page<Game> findAll(Pageable pageable) {
        Page<Game> entityPage = repository.findAll(pageable);
        List<Game> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Game update(Game entity, String id) {
        Optional<Game> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}