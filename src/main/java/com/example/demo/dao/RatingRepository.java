package com.example.demo.dao;

import com.example.demo.model.Rating;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends PagingAndSortingRepository<Rating, String> {
}