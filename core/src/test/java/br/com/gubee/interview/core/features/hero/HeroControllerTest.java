package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.enums.Race;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HeroController.class)
public class HeroControllerTest {
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private HeroService heroService;

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

        Mockito.when(heroService.findById(hero.getId())).thenReturn(hero);

        mockMvc.perform(get("/hero/findById/" + hero.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hero.getId().toString()))
                .andExpect(jsonPath("$.name").value(hero.getName()))
                .andExpect(jsonPath("$.race").value(hero.getRace().toString()))
                .andExpect(jsonPath("$.enabled").value(hero.getEnabled()))
                .andExpect(jsonPath("$.power_stats.id").value(hero.getPowerStats().getId().toString()))
                .andExpect(jsonPath("$.power_stats.strength").value(hero.getPowerStats().getStrength().toString()))
                .andExpect(jsonPath("$.power_stats.agility").value(hero.getPowerStats().getAgility().toString()))
                .andExpect(jsonPath("$.power_stats.dexterity").value(hero.getPowerStats().getDexterity().toString()))
                .andExpect(jsonPath("$.power_stats.intelligence").value(hero.getPowerStats().getIntelligence().toString()));
    }

    @Test
    public void testGetHeroByName() throws Exception {
        Hero hero = createHeroForTest();

        Mockito.when(heroService.findByName(hero.getName())).thenReturn(hero);

        mockMvc.perform(get("/hero/findByFilters?name=" + hero.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hero.getId().toString()))
                .andExpect(jsonPath("$.name").value(hero.getName()))
                .andExpect(jsonPath("$.race").value(hero.getRace().toString()))
                .andExpect(jsonPath("$.enabled").value(hero.getEnabled()))
                .andExpect(jsonPath("$.power_stats.id").value(hero.getPowerStats().getId().toString()))
                .andExpect(jsonPath("$.power_stats.strength").value(hero.getPowerStats().getStrength().toString()))
                .andExpect(jsonPath("$.power_stats.agility").value(hero.getPowerStats().getAgility().toString()))
                .andExpect(jsonPath("$.power_stats.dexterity").value(hero.getPowerStats().getDexterity().toString()))
                .andExpect(jsonPath("$.power_stats.intelligence").value(hero.getPowerStats().getIntelligence().toString()));
    }

    @Test
    public void testSaveHero() throws Exception {
        Hero hero = createHeroForTest();

        Mockito.when(heroService.save(Mockito.any())).thenReturn(hero);

        mockMvc.perform(post("/hero/save")
                        .content(mapper.writeValueAsString(hero))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hero.getId().toString()))
                .andExpect(jsonPath("$.name").value(hero.getName()))
                .andExpect(jsonPath("$.race").value(hero.getRace().toString()))
                .andExpect(jsonPath("$.enabled").value(hero.getEnabled()))
                .andExpect(jsonPath("$.power_stats.id").value(hero.getPowerStats().getId().toString()))
                .andExpect(jsonPath("$.power_stats.strength").value(hero.getPowerStats().getStrength().toString()))
                .andExpect(jsonPath("$.power_stats.agility").value(hero.getPowerStats().getAgility().toString()))
                .andExpect(jsonPath("$.power_stats.dexterity").value(hero.getPowerStats().getDexterity().toString()))
                .andExpect(jsonPath("$.power_stats.intelligence").value(hero.getPowerStats().getIntelligence().toString()));
    }

    @Test
    public void testUpdateHero() throws Exception {
        Hero hero = createHeroForTest();

        Mockito.when(heroService.update(Mockito.any())).thenReturn(hero);

        mockMvc.perform(put("/hero/update")
                        .content(mapper.writeValueAsString(hero))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hero.getId().toString()))
                .andExpect(jsonPath("$.name").value(hero.getName()))
                .andExpect(jsonPath("$.race").value(hero.getRace().toString()))
                .andExpect(jsonPath("$.enabled").value(hero.getEnabled()))
                .andExpect(jsonPath("$.power_stats.id").value(hero.getPowerStats().getId().toString()))
                .andExpect(jsonPath("$.power_stats.strength").value(hero.getPowerStats().getStrength().toString()))
                .andExpect(jsonPath("$.power_stats.agility").value(hero.getPowerStats().getAgility().toString()))
                .andExpect(jsonPath("$.power_stats.dexterity").value(hero.getPowerStats().getDexterity().toString()))
                .andExpect(jsonPath("$.power_stats.intelligence").value(hero.getPowerStats().getIntelligence().toString()));
    }

    @Test
    public void testDeleteHero() throws Exception {
        Hero hero = createHeroForTest();

        Mockito.when(heroService.delete(hero.getId())).thenReturn(hero);

        mockMvc.perform(delete("/hero/delete/" + hero.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hero.getId().toString()))
                .andExpect(jsonPath("$.name").value(hero.getName()))
                .andExpect(jsonPath("$.race").value(hero.getRace().toString()))
                .andExpect(jsonPath("$.enabled").value(hero.getEnabled()))
                .andExpect(jsonPath("$.power_stats.id").value(hero.getPowerStats().getId().toString()))
                .andExpect(jsonPath("$.power_stats.strength").value(hero.getPowerStats().getStrength().toString()))
                .andExpect(jsonPath("$.power_stats.agility").value(hero.getPowerStats().getAgility().toString()))
                .andExpect(jsonPath("$.power_stats.dexterity").value(hero.getPowerStats().getDexterity().toString()))
                .andExpect(jsonPath("$.power_stats.intelligence").value(hero.getPowerStats().getIntelligence().toString()));
    }
}
