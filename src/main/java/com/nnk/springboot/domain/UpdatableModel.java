package com.nnk.springboot.domain;

public interface UpdatableModel<M> {

    M update(M model);

    Integer getId();

}
