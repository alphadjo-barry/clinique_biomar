package com.exemple.clinique.service;

import java.util.List;

public interface AbstractService<T> {
    T save(T t);
    void delete(Long id);
    T findById(Long id);
    List<T> findAll();
    void update(Long id, T t);
}
