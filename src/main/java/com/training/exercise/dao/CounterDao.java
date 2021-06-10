package com.training.exercise.dao;

import com.training.exercise.model.Counter;

import java.util.HashMap;
import java.util.List;

public interface CounterDao {
        public List<Counter> findAll();
        public Counter findById(String id);
        public Counter save(Counter counter);
        public int delete(String id);
        public int update(String id);
}
