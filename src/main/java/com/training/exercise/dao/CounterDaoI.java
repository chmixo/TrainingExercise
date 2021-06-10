package com.training.exercise.dao;

import com.training.exercise.model.Counter;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CounterDaoI implements CounterDao {

    public static HashMap<String, Integer> counters = new HashMap<>();
    static {
        counters.put("abc", 5);
        counters.put("xyz", 3);
    }

    @Override
    public List<Counter> findAll() {
        List<Counter> counterList = new ArrayList<>();

        // We iterate through the DataBase (HashMap) to find all existing Counters
        Iterator it = counters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry counter = (Map.Entry)it.next();
            counterList.add(new Counter((String) counter.getKey(), (int) counter.getValue()));
            // avoids a ConcurrentModificationException
            it.remove();
        }
        return counterList;
    }

    @Override
    public Counter findById(String id) {
        // We iterate through the DataBase (HashMap) to find if the specified Id exists
        Iterator it = counters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry counter = (Map.Entry)it.next();
            if(counter.getKey().equals(id)){
                return new Counter((String) counter.getKey(), (int) counter.getValue());
            }
            // avoids a ConcurrentModificationException
            it.remove();
        }
        return null;
    }

    @Override
    public Counter save(Counter counter) {
        counters.put(counter.getName(), counter.getValue());
        return counter;
    }
}
