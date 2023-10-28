package org.example.repository;

import java.util.List;

public interface CRUD <T>{
    void upLoad();
    void save(T t);
    void upDate(T t);
    List<T> findAll();
    T findOne(String id);
    void delete(String id);
}
