package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.enums.Race;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@WebMvcTest(HeroController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
public class HeroControllerIT {
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    private Hero createHeroForTest() {
        Hero hero = new Hero();
        UUID heroId = UUID.fromString("65b88e11-036e-4369-9ffc-8b75aad5b423");
        hero.setId(heroId);
        hero.setName("32fdc81d-f172-4abd-b696-d9867d412ce4");
        hero.setRace(Race.CYBORG);
        hero.setEnabled(true);

        PowerStats powerStats = new PowerStats();
        UUID powerStatsId = UUID.fromString("1c5774bc-0b50-419f-9551-1ef65bdcc51b");
        powerStats.setId(powerStatsId);
        powerStats.setStrength((short) 8);
        powerStats.setAgility((short) 7);
        powerStats.setDexterity((short) 3);
        powerStats.setIntelligence((short) 1);

        hero.setPowerStats(powerStats);

        return hero;
    }

    @BeforeAll
    public void setup() throws Exception {
        Hero hero = createHeroForTest();

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
    public void testGetHeroById() throws Exception {
        Hero hero = createHeroForTest();

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
}
