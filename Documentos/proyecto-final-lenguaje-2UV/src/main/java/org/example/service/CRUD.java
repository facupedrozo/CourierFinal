package org.example.service;

import java.util.List;

public interface CRUD <T>{
    void save(T t);

    void upDate(T t);

    T findOne(String cuit);
    List<T> findAll();

    void delete(String cuit);
}
