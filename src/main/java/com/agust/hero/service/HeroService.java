package com.agust.hero.service;

import com.agust.hero.entity.Hero;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HeroService {
    List<Hero> findAll();

    Hero findById(long id);
}
