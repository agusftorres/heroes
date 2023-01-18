package com.agust.hero.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroServiceImplTest {
    private final long ID = 12L;
    private final Hero HERO = Hero.builder()
            .id(ID)
            .name("Superman")
            .identity("Clark Kent")
            .build();

    @Mock
    private HeroRepository heroRepository;

    @Test
    public void testFindAllHeroes_everythingIsOk_shouldReturnAHeroesList(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();

        when(heroRepository.findAll()).thenReturn(getHeroesList());

        Assertions.assertNotNull(heroService.findAll());
    }

}
