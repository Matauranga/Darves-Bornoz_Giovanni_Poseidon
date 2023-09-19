package com.nnk.springboot.services;

import com.nnk.springboot.domain.UpdatableModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Slf4j
public abstract class AbstractCrudService<M extends UpdatableModel<M>> implements CrudServiceInterface<M> {

    protected final JpaRepository<M, Integer> repository;

    public AbstractCrudService(JpaRepository<M, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public M add(M model) {
        log.debug("Save " + model.getClass().getName() + " with value = " + model);
        return this.repository.save(model);
    }

    @Override
    public M getById(Integer id) {
        final M entity = this.repository.findById(id).orElseThrow();
        log.debug("Get " + entity.getClass().getName() + " with id = " + id);
        return entity;
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
    public void update(M model) {
        log.debug("Update " + model.getClass().getName() + " with value = " + model);

        final M updatedEntity = this.repository
                .findById(model.getId())
                .orElseThrow() // ToDo : create a NotFoundException
                .update(model);

        repository.save(updatedEntity);

    }
}
