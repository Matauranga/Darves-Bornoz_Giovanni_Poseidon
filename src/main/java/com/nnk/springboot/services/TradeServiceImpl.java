package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl extends AbstractCrudService<Trade> {
    public TradeServiceImpl(TradeRepository repository) {
        super(repository);
    }
}
