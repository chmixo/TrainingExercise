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

    @GetMapping(value = "/counters")
    public String getCounters(){
        return counterDao.findAll().toString();
    }

    @GetMapping(value = "/counters/{counter}")
    public String getCounter(@PathVariable String counter){
        return counterDao.findById(counter).toString();
    }


}
