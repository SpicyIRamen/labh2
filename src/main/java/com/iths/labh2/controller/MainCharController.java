package com.iths.labh2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iths.labh2.Hero.Hero;
import com.iths.labh2.repository.MainCharRepository;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/db")
public class MainCharController {

    @GetMapping("/index")
    public String index() {
        return "Test index";
    }

    @Autowired
    private MainCharRepository mainCharRepository;

    @GetMapping("/heroes")
    public List<Hero> getAllHeroes() {
        return mainCharRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable(value = "id") Long heroId)
            throws ResourceNotFoundException {
        Hero hero = mainCharRepository.findById(heroId)
                .orElseThrow(() -> new ResourceNotFoundException("Main Character not found for this id :: " + heroId));
        return ResponseEntity.ok().body(hero);
    }

    @PostMapping("/hero")
    public Hero createHero(@Valid @RequestBody Hero hero) {
        return mainCharRepository.save(hero);
    }

    @GetMapping("/hero/{hero}")
    public List<Hero> findAllByHero(@PathVariable String hero) {
        if (mainCharRepository.findAllByHero(hero).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else
            return mainCharRepository.findAllByHero(hero);
    }

    @GetMapping("/movie/{movie}")
    public List<Hero> findAllByMovie(@PathVariable String movie) {
        if (mainCharRepository.findAllByMovie(movie).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else
            return mainCharRepository.findAllByMovie(movie);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteHero(@RequestBody Hero hero) {
        if(mainCharRepository.existsById(hero.getId())) {
            mainCharRepository.delete(hero);
            System.out.println("Hero deleted.");
            return ResponseEntity.ok("Deleted entry with ID: " + hero.getId());
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateHero(@RequestBody Hero hero) {
        if (!mainCharRepository.existsById(hero.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
        }else
            mainCharRepository.save(hero);
        System.out.println("Hero has been updated.");
        return ResponseEntity.ok("Entry updated to:\n" + hero.getHero() +
                "\n" + hero.getMovie() + "\n");
    }
}