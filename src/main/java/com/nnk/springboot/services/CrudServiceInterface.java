package com.nnk.springboot.services;

import com.nnk.springboot.domain.UpdatableModel;

import java.util.List;

public interface CrudServiceInterface<M extends UpdatableModel<M>> {

    M add(M model);

    void update(M model);

    M getById(Integer id);

    List<M> getAll();

    void deleteById(Integer id);

}
