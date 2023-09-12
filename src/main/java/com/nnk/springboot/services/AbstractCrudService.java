package com.nnk.springboot.services;

import com.nnk.springboot.domain.UpdatableModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractCrudService<M extends UpdatableModel<M>> implements CrudServiceInterface<M>{

    protected final JpaRepository<M, Integer> repository;

    public AbstractCrudService(JpaRepository<M, Integer> repository){
        this.repository = repository;
    }

    @Override
    public M add(M model) {
        return this.repository.save(model);
    }

    @Override
    public M getById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public List<M> getAll() {
        return this.repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    @Override
    public void update(M model){

        var updatedEntity = this.repository
                .findById(model.getId())
                .orElseThrow()
                .update(model);

        repository.save(updatedEntity);

    }
}
