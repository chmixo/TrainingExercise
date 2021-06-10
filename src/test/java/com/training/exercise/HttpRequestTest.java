package com.training.exercise;

import com.training.exercise.model.Counter;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // Test de la requete GET localhost:port/counters
    @Test
    @Order(1)
    public void getCounterShouldReturnMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counters",
                String.class)).isEqualTo("[{\"abc\": 5}, {\"xyz\": 3}]");
    }

    // Test de la requete GET localhost:port/counters/:counter si :counter existe
    @Test
    @Order(2)
    public void getCounterCounterShouldReturnMesssage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counters/abc",
                String.class)).isEqualTo("{\"abc\": 5}");
    }

    // Test de la requete GET localhost:port/counters/:counter si :counter n'existe pas
    @Test
    @Order(3)
    public void getCounterCounterShouldReturn404Error() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counters/abz",
                String.class)).contains("\"status\":404");
    }

    // Test de la requete PUT localhost:port/counters/:counter si :counter existe
    @Test
    @Order(4)
    public void putCounterCounterShouldIncreaseValue() throws Exception {
        this.restTemplate.put("http://localhost:" + port + "/counters/abc",String.class);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counters/abc",
                String.class)).isEqualTo("{\"abc\": 6}");
    }

    // Test de la requete PUT localhost:port/counters/:counter si :counter n'existe pas
    @Test
    @Order(5)
    public void putCounterCounterShouldReturn404Error() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/counters/abz",
                HttpMethod.PUT, new HttpEntity<String>(""), String.class);

        assertThat(response.getStatusCode().toString()).isEqualTo("404 NOT_FOUND");
    }

    // Test de la requete DELETE localhost:port/counters/:counter si :counter existe et sa valeur superieur a 0
    @Test
    @Order(6)
    public void deleteCounterCounterShouldDecreaseValue() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/counters/abc",
                HttpMethod.DELETE, new HttpEntity<String>(""), String.class);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counters/abc",
                String.class)).contains("{\"abc\": 5}");
    }

    // Test de la requete DELETE localhost:port/counters/:counter si :counter existe et sa valeur egale a 0
    @Test
    @Order(7)
    public void deleteCounterCounterShouldDeleteCounter() throws Exception {
        for(int i = 0; i < 6; i++){
            this.restTemplate.delete("http://localhost:" + port + "/counters/abc",String.class);
        }
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counters/abc",
                String.class)).contains("\"status\":404");
    }

    // Test de la requete DELETE localhost:port/counters/:counter si :counter existe et sa valeur superieur a 0
    @Test
    @Order(8)
    public void deleteCounterCounterShouldReturn404Error() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/counters/rtsds",
                HttpMethod.DELETE, new HttpEntity<String>(""), String.class);
        assertThat(response.getStatusCode().toString()).isEqualTo("404 NOT_FOUND");
    }

    // Test de la requete POST localhost:port/counters/:counter
    @Test
    @Order(9)
    public void postCounterShouldCreateNewCounter() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject counterJsonObject = new JSONObject();
        counterJsonObject.put("name", "counterTest");
        counterJsonObject.put("value", 8);

        HttpEntity<String> request =
                new HttpEntity<String>(counterJsonObject.toString(), headers);

        this.restTemplate.postForObject("http://localhost:" + port + "/counters",
                request,
                String.class);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counters",
                String.class)).contains("{\"counterTest\": 8}");
    }
}
