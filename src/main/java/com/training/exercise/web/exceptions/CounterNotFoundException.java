package com.training.exercise.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CounterNotFoundException extends RuntimeException {

    public CounterNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public static void checkForNotFoundException(int response){
        if(response == -1 ){
            throw new CounterNotFoundException("The counter you are looking to modify does not exist");
        }
    }
}
