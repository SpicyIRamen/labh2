package com.iths.labh2.repository;

import com.iths.labh2.Hero.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "heroes", path = "heroes")
public interface MainCharRepository extends JpaRepository<Hero, Long>{

        List<Hero> findAllByHero(String hero);
        List<Hero> findAllByMovie(String movie);
        Hero findById(long id);


}
