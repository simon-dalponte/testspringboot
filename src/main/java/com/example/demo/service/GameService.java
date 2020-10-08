package com.example.demo.service;

import com.example.demo.model.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameService extends GenericService<Game, String> {
    List<Game> findAll();

    Optional<Game> findById(String id);

    Game save(Game employee);

    void deleteById(String id);
}