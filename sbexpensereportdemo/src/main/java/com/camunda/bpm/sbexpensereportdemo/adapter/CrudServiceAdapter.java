package com.camunda.bpm.sbexpensereportdemo.adapter;

import java.util.List;

public interface CrudServiceAdapter<T> {

    void merge(T object);
    List<T> findAll();
    T findById(Long id);
    void delete(Long id);
}
