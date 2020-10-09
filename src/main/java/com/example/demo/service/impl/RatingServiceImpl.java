package com.example.demo.service.impl;

import com.example.demo.dao.RatingRepository;
import com.example.demo.dto.RatingDTO;
import com.example.demo.mapper.RatingMapper;
import com.example.demo.model.Rating;
import com.example.demo.service.RatingService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {
    private final RatingRepository repository;

    public RatingServiceImpl(RatingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Rating save(Rating entity) {
        return repository.save(entity);
    }

    @Override
    public List<Rating> save(List<Rating> entities) {
        return (List<Rating>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Rating> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Rating> findAll() {
        return (List<Rating>) repository.findAll();
    }

    @Override
    public Page<Rating> findAll(Pageable pageable) {
        Page<Rating> entityPage = repository.findAll(pageable);
        List<Rating> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Rating update(Rating entity, String id) {
        Optional<Rating> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}