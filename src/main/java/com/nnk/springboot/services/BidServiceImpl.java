package com.nnk.springboot.services;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidRepository;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl extends AbstractCrudService<Bid> {

    public BidServiceImpl(BidRepository repository) {
        super(repository);
    }

}
