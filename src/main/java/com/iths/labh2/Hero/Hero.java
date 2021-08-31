package com.iths.labh2.Hero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "heroes")
public class Hero {

    private long id;
    private String hero;
    private String movie;


    public Hero() {

    }

    public Hero(String hero, String movie) {
        this.hero = hero;
        this.movie = movie;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "hero")
    public String getHero() {
        return hero;
    }
    public void setHero(String hero) {
        this.hero = hero;
    }

    @Column(name = "movie", nullable = false)
    public String getMovie() {
        return movie;
    }
    public void setMovie(String movie) {
        this.movie = movie;
    }


    @Override
    public String toString() {
        return String.format ("Main Character [id=%d, hero='%s', movie='%s']",
                id, hero, movie);
        }


}