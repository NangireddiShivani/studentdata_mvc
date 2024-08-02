package com.example.demo.repositary;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.studentmodel;

public interface studentrepositary extends CrudRepository<studentmodel, Integer> {

}
