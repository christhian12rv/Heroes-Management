package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.hero.impl.HeroServiceImpl;
import br.com.gubee.interview.enums.Race;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {
    @Mock
    HeroRepository heroRepository;

    @InjectMocks
    HeroServiceImpl heroService;

    private Hero createHeroForTest() {
        Hero hero = new Hero();
        UUID heroId = UUID.fromString("87b49b17-d22d-4bca-b757-b804b7f00b1f");
        hero.setId(heroId);
        hero.setName("Teste");
        hero.setRace(Race.CYBORG);
        hero.setEnabled(true);

        PowerStats powerStats = new PowerStats();
        UUID powerStatsId = UUID.fromString("a9337a63-5c11-4d38-be27-cc7bd09ddae0");
        powerStats.setId(powerStatsId);
        powerStats.setStrength((short) 8);
        powerStats.setAgility((short) 7);
        powerStats.setDexterity((short) 3);
        powerStats.setIntelligence((short) 1);

        hero.setPowerStats(powerStats);

        return hero;
    }

    @Test
    public void testGetHeroById() throws Exception {
        Hero hero = createHeroForTest();

        Mockito.when(heroRepository.findById(hero.getId())).thenReturn(hero);

        Hero heroFound = heroService.findById(hero.getId());

        Assertions.assertEquals(hero, heroFound);
    }

    @Test
    public void testGetHeroByName() throws Exception {
        Hero hero = createHeroForTest();

        Mockito.when(heroRepository.findByName(hero.getName())).thenReturn(hero);

        Hero heroFound = heroService.findByName(hero.getName());

        Assertions.assertEquals(hero, heroFound);
    }

    @Test
    public void testSaveHero() throws Exception {
        Hero hero = createHeroForTest();

        Hero heroFound = heroService.save(hero);

        Assertions.assertEquals(hero, heroFound);
    }

    @Test
    public void testUpdateHero() throws Exception {
        Hero hero = createHeroForTest();

        Mockito.when(heroRepository.findById(hero.getId())).thenReturn(hero);

        Hero heroFound = heroService.update(hero);

        Assertions.assertEquals(hero, heroFound);
    }

    @Test
    public void testDeleteHero() throws Exception {
        Hero hero = createHeroForTest();

        Mockito.when(heroRepository.findById(hero.getId())).thenReturn(hero);

        Hero heroFound = heroService.delete(hero.getId());

        Assertions.assertEquals(hero, heroFound);
    }
}
