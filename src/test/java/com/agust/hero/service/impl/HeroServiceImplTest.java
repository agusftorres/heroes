package com.agust.hero.service.impl;

import com.agust.hero.entity.Hero;
import com.agust.hero.repository.HeroRepository;
import com.agust.hero.service.HeroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

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


    @Test
    public void testFindById_heroExists_shouldReturnAHero(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();

        when(heroRepository.findById(ID)).thenReturn(Optional.ofNullable(HERO));

        final Hero actual = heroService.findById(ID);
        Assertions.assertEquals(HERO, actual);
    }

    @Test
    public void testFindById_heroNotFound_shouldThrowHeroNotFoundException(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();

        when(heroRepository.findById(ID)).thenReturn(Optional.empty());

        try {
            heroService.findById(ID);
        } catch(HeroNotFoundException ex){
            Assertions.assertEquals(ErrorCode.HERO_NOT_FOUND, ex.getCode());
        }
    }

    private List<Hero> getHeroesList() {
        return List.of(HERO);
    }

}
