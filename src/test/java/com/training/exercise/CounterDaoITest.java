package com.training.exercise;

import com.training.exercise.dao.CounterDao;
import com.training.exercise.dao.CounterDaoI;
import com.training.exercise.model.Counter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CounterDaoITest {

    // Test de la methode FindAll
    @Test
    @Order(1)
    public void findAllMethodShouldFindAllCounters(){
        List<Counter> expected = Arrays.asList(new Counter("abc", 5), new Counter("xyz", 3));
        CounterDao counterDao = new CounterDaoI();

        List<Counter> result = counterDao.findAll();

        assertThat(result.toString()).isEqualTo(expected.toString());
    }

    // Test de la methode findById si le id existe
    @Test
    @Order(2)
    public void findByIdMethodShouldReturnCounterIfExists(){
        Counter expected = new Counter("abc", 5);
        CounterDao counterDao = new CounterDaoI();

        Counter result = counterDao.findById("abc");

        assertThat(result.toString()).isEqualTo(expected.toString());
    }

    // Test de la methode findById si le id n' existe pas
    @Test
    @Order(3)
    public void findByIdMethodShouldReturnNull(){
        CounterDao counterDao = new CounterDaoI();

        Counter result = counterDao.findById("aasdasd");

        assertThat(result).isNull();
    }

    // Test de la methode save
    @Test
    @Order(4)
    public void saveMethodShouldSaveCounter(){
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("abc", 5);
        expected.put("xyz", 3);
        expected.put("counterTest", 8);

        CounterDao counterDao = new CounterDaoI();

        counterDao.save(new Counter("counterTest", 8));

        assertThat( CounterDaoI.counters).isEqualTo(expected);
    }

    // Test de la methode delete si le id existe
    @Test
    @Order(5)
    public void deleteMethodShouldReturn0OnSuccess(){
        int expected = 0;
        CounterDao counterDao = new CounterDaoI();

        int result = counterDao.delete("abc");

        assertThat(result).isEqualTo(expected);
    }

    // Test de la methode delete si le id n'existe pas
    @Test
    @Order(6)
    public void deleteMethodShouldReturnErrorExitCode(){
        int expected = -1;
        CounterDao counterDao = new CounterDaoI();

        int result = counterDao.delete("hasvduas");

        assertThat(result).isEqualTo(expected);
    }

    // Test de la methode update si le id existe
    @Test
    @Order(7)
    public void updateMethodShouldReturn0OnSuccess(){
        int expected = 0;
        CounterDao counterDao = new CounterDaoI();

        int result = counterDao.update("abc");

        assertThat(result).isEqualTo(expected);
    }

    // Test de la methode update si le id n'existe pas
    @Test
    @Order(8)
    public void updateMethodShouldReturnErrorExitCode(){
        int expected = -1;
        CounterDao counterDao = new CounterDaoI();

        int result = counterDao.update("uhjwsvddw");

        assertThat(result).isEqualTo(expected);
    }

    // Test de la methode update si le id n'existe pas
    @Test
    @Order(9)
    public void deleteMethodShouldDeleteCounterIfValueIs0(){
        List<Counter> expected = Arrays.asList(new Counter("abc", 5), new Counter("xyz", 3));
        CounterDao counterDao = new CounterDaoI();

        for(int i=0; i <= 8 ; i++){
            counterDao.delete("counterTest");
        }
        List<Counter> result = counterDao.findAll();

        assertThat(result.toString()).isEqualTo(expected.toString());
    }
}
