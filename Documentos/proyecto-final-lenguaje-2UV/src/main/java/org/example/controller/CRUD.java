package org.example.controller;

import java.util.List;

public interface CRUD <T>{
    void create(T t);
    T findOne(String id);
    List<T> findAll();
    void upDate(T t);
    void delete(String id);
}
