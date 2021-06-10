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

    // Returns a List of All existing Counters
    @Override
    public List<Counter> findAll() {
        // We iterate through the DataBase (HashMap) to find all existing Counters
        List<Counter> counterList = new ArrayList<>();
        Iterator it = counters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry counter = (Map.Entry)it.next();
            counterList.add(new Counter((String) counter.getKey(), (int) counter.getValue()));
        }
        return counterList;
    }

    //Return the Counter specified by the id, return null if it does not exist
    @Override
    public Counter findById(String id) {
        // We iterate through the DataBase (HashMap) to find if the specified Id exists
        Iterator it = counters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry counter = (Map.Entry)it.next();
            if(counter.getKey().equals(id)){
                return new Counter((String) counter.getKey(), (int) counter.getValue());
            }
        }
        return null;
    }

    @Override
    public Counter save(Counter counter) {
        counters.put(counter.getName(), counter.getValue());
        return counter;
    }

    // Return 0 if the delete operation was a success, -1 if not
    @Override
    public int delete(String id) {
        if(counters.containsKey(id)){
            if(counters.get(id) > 0){
                counters.put(id, counters.get(id) - 1 );
                return 0;
            } else {
                counters.remove(id);
                return 0;
            }
        } else{
            return -1;
        }
    }

    // Return 0 if the update operation was a success, -1 if not
    @Override
    public int update(String id) {
        if(counters.containsKey(id)){
            counters.put(id, counters.get(id) + 1 );
            return 0;
        } else {
            return -1;
        }
    }
}
