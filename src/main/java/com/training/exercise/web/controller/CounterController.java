package com.training.exercise.web.controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class CounterController {

    @GetMapping(value = "/counters")
    public String getCounters(){
        return "This is a counter test";
    }

    @GetMapping(value = "/counters/{counter}")
    public String getCounter(@PathVariable String counter){
        return ("You asked for counter " + counter);
    }


}
