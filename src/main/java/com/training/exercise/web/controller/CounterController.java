package com.training.exercise.web.controller;
import com.training.exercise.dao.CounterDao;
import com.training.exercise.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Void> addCounter(@RequestBody Counter counter) {
        Counter addedCounter = counterDao.save(counter);

        // Si le counter contenu dans la requete est null, on retourne le code 204 No Content.
        if(addedCounter == null){
            return ResponseEntity.noContent().build();
        }

        // Creation d'une URI pour le code de retour HTTP 201
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{counter}")
                .buildAndExpand(addedCounter.getName())
                .toUri();

        // Renvoyer le code HTTP statut 201 en avec l'URI comme argument
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping (value = "/counters/{counter}")
    public void deleteCounter(@PathVariable String counter) {
        counterDao.delete(counter);
    }

    @PutMapping (value = "/counters/{counter}")
    public void updateProduit(@PathVariable String counter) {
        counterDao.update(counter);
    }

}
