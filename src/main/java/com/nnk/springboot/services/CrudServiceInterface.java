package com.nnk.springboot.services;

import com.nnk.springboot.domain.UpdatableModel;

import java.util.List;

public interface CrudServiceInterface<M extends UpdatableModel<M>> {
    /**
     * Method to add a model
     */
    M add(M model);

    /**
     * Method to update a model
     */
    void update(M model);

    /**
     * Method to get a model by ID
     */
    M getById(Integer id);

    /**
     * Method to get all model
     */
    List<M> getAll();

    /**
     * Method to delete a model
     */
    void deleteById(Integer id);

}
