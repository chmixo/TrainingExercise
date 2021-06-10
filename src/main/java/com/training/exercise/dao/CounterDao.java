package com.training.exercise.dao;

import com.training.exercise.model.Counter;

import java.util.HashMap;
import java.util.List;

public interface CounterDao {
        public List<Counter> findAll();
        public Counter findById(String id);
        public Counter save(Counter counter);
        public void delete(String id);
        public void update(String id);
}
