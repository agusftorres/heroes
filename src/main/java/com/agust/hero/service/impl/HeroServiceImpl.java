package com.agust.hero.service.impl;

import com.agust.hero.entity.Hero;
import com.agust.hero.exception.HeroNotFoundException;
import com.agust.hero.repository.HeroRepository;
import com.agust.hero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService {
    private static final String INFORMATION = "Heroe eliminado.";

    @Autowired
    HeroRepository heroRepository;

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public Hero findById(long id) {
        return heroRepository.findById(id).orElseThrow(HeroNotFoundException::new);
    }

    @Override
    public Hero update(Hero hero) {
        Optional<Hero> existingHero = heroRepository.findById(hero.getId());
        return heroRepository.save(
                existingHero.orElseThrow(HeroNotFoundException::new)
        );
    }

    @Override
    public String delete(long id) {
        Hero hero = heroRepository.findById(id).orElseThrow(HeroNotFoundException::new);
        heroRepository.deleteById(hero.getId());
        return INFORMATION;
    }
}
