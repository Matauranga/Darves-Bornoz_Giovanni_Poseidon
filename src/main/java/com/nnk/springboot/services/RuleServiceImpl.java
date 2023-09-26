package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import org.springframework.stereotype.Service;

@Service
public class RuleServiceImpl extends AbstractCrudService<Rule> {
    public RuleServiceImpl(RuleRepository repository) {
        super(repository);
    }
}
