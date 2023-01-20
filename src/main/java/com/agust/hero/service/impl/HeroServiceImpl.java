package com.agust.hero.service.impl;

import com.agust.hero.entity.Hero;
import com.agust.hero.exception.HeroNotFoundException;
import com.agust.hero.exceptionhandler.ErrorCode;
import com.agust.hero.exceptionhandler.GenericException;
import com.agust.hero.repository.HeroRepository;
import com.agust.hero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService {

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
}
