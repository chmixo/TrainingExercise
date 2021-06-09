package com.training.exercise.web.controller;
import com.training.exercise.model.Counter;
import org.springframework.web.bind.annotation.*;

@RestController
public class CounterController {

    @GetMapping(value = "/counters")
    public String getCounters(){
        return "This is a counter test";
    }

    @GetMapping(value = "/counters/{counter}")
    public String getCounter(@PathVariable String counter){
        Counter exempleCounter = new Counter(counter, 10);
        return exempleCounter.toString();
    }


}
