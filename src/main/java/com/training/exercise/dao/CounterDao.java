package com.training.exercise.dao;

import com.training.exercise.model.Counter;

import java.util.HashMap;
import java.util.List;

public interface CounterDao {
        public HashMap<String, Integer> findAll();
        public Counter findById(String id);
        public Counter save(Counter counter);
}
