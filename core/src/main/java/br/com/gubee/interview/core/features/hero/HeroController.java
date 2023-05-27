package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/hero")
public class HeroController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeroController.class);

    @Autowired
    private HeroService heroService;

    @GetMapping("/findById/{id}")
    @ResponseBody
    public ResponseEntity findById(@PathVariable UUID id) {
        LOGGER.info("Calling findById of HeroController");

        try {
            Hero hero = heroService.findById(id);
            if (hero == null)
                return ResponseEntity.status(404).build();
            return ResponseEntity.ok(hero);
        } catch (Exception e) {
            LOGGER.error("An internal error occurred on finding hero by id " + id + ": " + e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findByFilters")
    @ResponseBody
    public ResponseEntity findByFilters(@Param("name") String name) {
        LOGGER.info("Calling findByFilters of HeroController");

        try {
            Hero hero = heroService.findByName(name);

            if (hero == null)
                return ResponseEntity.status(200).build();

            return ResponseEntity.ok(hero);
        } catch (Exception e) {
            LOGGER.error("An internal error occurred on finding hero by name " + name + ": " + e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Hero hero) {
        LOGGER.info("Calling save of HeroController");

        try {
            heroService.save(hero);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("An internal error occurred on saving hero: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PutMapping("/update")
//    public ResponseEntity
}
