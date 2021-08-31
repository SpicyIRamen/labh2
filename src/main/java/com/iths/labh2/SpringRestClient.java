package com.iths.labh2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.iths.labh2.Hero.Hero;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class SpringRestClient {

    private static final String GET_HEROES_ENDPOINT_URL = "http://localhost:8080/db/heroes";
    private static final String GET_HERO_ENDPOINT_URL = "http://localhost:8080/db/heroes/{id}";
    private static final String CREATE_HERO_ENDPOINT_URL = "http://localhost:8080/db/heroes";
    private static final String UPDATE_HERO_ENDPOINT_URL = "http://localhost:8080/db/heroes/{id}";
    private static final String DELETE_HERO_ENDPOINT_URL = "http://localhost:8080/db/heroes/{id}";
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringRestClient springRestClient = new SpringRestClient();

        // Step1: first create a new
        springRestClient.createHero();

        // Step 2: get new created from step1
        springRestClient.getHeroById();

        // Step3: get all
        springRestClient.getAllHeroes();

        // Step4: Update with id = 1
        springRestClient.updateHero();

        // Step5: Delete with id = 1
        springRestClient.deleteHero();
    }

    private void getAllHeroes() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(GET_HEROES_ENDPOINT_URL, HttpMethod.GET, entity,
                String.class);

        System.out.println(result);
    }

    private void getHeroById() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        RestTemplate restTemplate = new RestTemplate();
        Hero result = restTemplate.getForObject(GET_HERO_ENDPOINT_URL, Hero.class, params);

        System.out.println(result);
    }

    private void createHero() {

        Hero newHero = new Hero("Aragorn", "LotR");

        RestTemplate restTemplate = new RestTemplate();
        Hero result = restTemplate.postForObject(CREATE_HERO_ENDPOINT_URL, newHero, Hero.class);

        System.out.println(result);
    }

    private void updateHero() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        Hero updatedHero = new Hero("Aragorn", "LotR");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_HERO_ENDPOINT_URL, updatedHero, params);
    }

    private void deleteHero() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_HERO_ENDPOINT_URL, params);
    }
}