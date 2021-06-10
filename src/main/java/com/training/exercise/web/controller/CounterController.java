package com.training.exercise.web.controller;
import com.training.exercise.dao.CounterDao;
import com.training.exercise.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CounterController {

    @Autowired
    private CounterDao counterDao;

    // GET /counters/
    @GetMapping(value = "/counters")
    public String getCounters(){
        return counterDao.findAll().toString();
    }

    // GET /counters/:counter
    @GetMapping(value = "/counters/{counter}")
    public String getCounter(@PathVariable String counter){
        return counterDao.findById(counter).toString();
    }

    //POST /counter/
    @PostMapping(value = "/counters")
    public void addCounter(@RequestBody Counter counter) {
        counterDao.save(counter);
    }

}
