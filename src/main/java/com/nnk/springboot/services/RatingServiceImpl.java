package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl extends AbstractCrudService<Rating> {
    public RatingServiceImpl(RatingRepository repository) {
        super(repository);
    }
}
