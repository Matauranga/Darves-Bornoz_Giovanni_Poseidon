package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

@Service
public class RuleNameServiceImpl extends AbstractCrudService<RuleName> {
    public RuleNameServiceImpl(RuleNameRepository repository) {
        super(repository);
    }
}