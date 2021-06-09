package com.training.exercise.dao;

import com.training.exercise.model.Counter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CounterDaoI implements CounterDao {

    public static HashMap<String, Integer> counters = new HashMap<>();
    static {
        counters.put("abc", 5);
        counters.put("xyz", 3);
    }

    @Override
    public HashMap<String, Integer> findAll() {
        return counters;
    }

    @Override
    public Counter findById(String id) {
        // We iterate through the HashMap to find if the specified Id exists
        Iterator it = counters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry counter = (Map.Entry)it.next();
            if(counter.getKey().equals(id)){
                return new Counter((String) counter.getKey(), (int) counter.getValue());
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        return null;
    }

    @Override
    public Counter save(Counter counter) {
        counters.put(counter.getName(), counter.getValue());
        return counter;
    }
}
