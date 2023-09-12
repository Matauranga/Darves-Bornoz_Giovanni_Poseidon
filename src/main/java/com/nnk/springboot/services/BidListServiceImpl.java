package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

@Service
public class BidListServiceImpl extends AbstractCrudService<BidList> {

    public BidListServiceImpl(BidListRepository repository){
        super(repository);
    }

}