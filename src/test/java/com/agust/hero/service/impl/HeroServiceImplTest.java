package com.agust.hero.service.impl;

import com.agust.hero.entity.Hero;
import com.agust.hero.exception.HeroNotFoundException;
import com.agust.hero.repository.HeroRepository;
import com.agust.hero.service.HeroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroServiceImplTest {
    public static final String INFORMATION = "Heroe eliminado.";
    private final long ID = 12L;
    private final Hero HERO = Hero.builder()
            .id(ID)
            .name("Capitan America")
            .identity("Steve Rogers")
            .build();
    private final Hero HERO_2 = Hero.builder()
            .id(ID)
            .name("Capitan America")
            .identity("Sam Wilson")
            .build();

    @Mock
    private HeroRepository heroRepository;

    @Test
    public void testFindAllHeroes_everythingIsOk_shouldReturnAHeroesList(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();
        ReflectionTestUtils.setField(heroService, "heroRepository", heroRepository);

        when(heroRepository.findAll()).thenReturn(getHeroesList());

        Assertions.assertNotNull(heroService.findAll());
    }


    @Test
    public void testFindById_heroExists_shouldReturnAHero(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();
        ReflectionTestUtils.setField(heroService, "heroRepository", heroRepository);

        when(heroRepository.findById(ID)).thenReturn(Optional.ofNullable(HERO));

        final Hero actual = heroService.findById(ID);
        Assertions.assertEquals(HERO, actual);
    }

    @Test
    public void testFindById_heroNotFound_shouldThrowHeroNotFoundException(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();
        ReflectionTestUtils.setField(heroService, "heroRepository", heroRepository);

        when(heroRepository.findById(ID)).thenReturn(Optional.empty());
        Throwable throwable = catchThrowable(() -> heroService.findById(ID));
        assertThat(throwable)
                .isInstanceOf(HeroNotFoundException.class);
    }

    @Test
    public void testUpdateHero_everythingIsOk_shouldReturnAUpdatedHero(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();
        ReflectionTestUtils.setField(heroService, "heroRepository", heroRepository);

        when(heroRepository.findById(ID)).thenReturn(Optional.of(HERO_2));
        when(heroRepository.save(HERO_2)).thenReturn(HERO_2);

        final Hero updated = heroService.update(HERO_2);
        Assertions.assertEquals(HERO_2, updated);
    }

    @Test
    public void testUpdateHero_NotFound_shouldThrowHeroNotFoundException(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();
        ReflectionTestUtils.setField(heroService, "heroRepository", heroRepository);

        when(heroRepository.findById(ID)).thenReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> heroService.update(HERO_2));
        assertThat(throwable)
                .isInstanceOf(HeroNotFoundException.class);
    }

    @Test
    public void testDeleteHero_everythingIsOk_shouldReturnDeletedInformation(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();
        ReflectionTestUtils.setField(heroService, "heroRepository", heroRepository);

        when(heroRepository.findById(ID)).thenReturn(Optional.of(HERO));

        final String deleted = heroService.delete(ID);
        Assertions.assertEquals(INFORMATION, deleted);
    }

    @Test
    public void testDeleteHero_notFound_shouldThrowHeroNotFound(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();
        ReflectionTestUtils.setField(heroService, "heroRepository", heroRepository);

        when(heroRepository.findById(ID)).thenReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> heroService.delete(ID));
        assertThat(throwable)
                .isInstanceOf(HeroNotFoundException.class);
    }

    @Test
    public void testFindHeroByString_everythingIsOk_shouldReturnAHero(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();
        ReflectionTestUtils.setField(heroService, "heroRepository", heroRepository);

        when(heroRepository.findByNameContaining(anyString())).thenReturn(getHeroesList());

        final List<Hero> heroList = heroService.findByString("cap");
        Assertions.assertNotNull(heroList);
    }

    @Test
    public void testFindHeroByString_thereAreNotAnyHero_shouldReturnEmptyList(){
        heroRepository = mock(HeroRepository.class);
        HeroService heroService = new HeroServiceImpl();
        ReflectionTestUtils.setField(heroService, "heroRepository", heroRepository);

        when(heroRepository.findByNameContaining(anyString())).thenReturn(List.of());

        final List<Hero> heroList = heroService.findByString("cap");
        Assertions.assertEquals(0,heroList.size());
    }
    private List<Hero> getHeroesList() {
        return List.of(HERO);
    }

}
