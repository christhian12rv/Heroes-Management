package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.Exceptions.HeroNotFoundException;
import br.com.gubee.interview.model.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping(value = "/hero", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
        } catch(IllegalArgumentException e) {
            LOGGER.error("Illegal Argument occurred when finding hero by id " + id + ": " + e.getMessage() );
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("An internal error occurred when finding hero by id " + id + ": " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
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
        } catch(IllegalArgumentException e) {
            LOGGER.error("Illegal Argument occurred when finding hero by name " + name + ": " + e.getMessage() );
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("An internal error occurred when finding hero by name " + name + ": " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Hero hero) {
        LOGGER.info("Calling save of HeroController");

        try {
            return ResponseEntity.ok(heroService.save(hero));
        } catch(IllegalArgumentException e) {
            LOGGER.error("Illegal Argument occurred when saving hero: " + e.getMessage() );
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }  catch (Exception e) {
            LOGGER.error("An internal error occurred when saving hero: " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Hero hero) {
        LOGGER.info("Calling update of HeroController");

        try {
            return ResponseEntity.ok(heroService.update(hero));
        } catch(IllegalArgumentException e) {
            LOGGER.error("Illegal Argument occurred when updating hero: " + e.getMessage() );
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (HeroNotFoundException e) {
            LOGGER.error("A Hero with id : " + hero.getId() + " not found when updating hero");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("An internal error occurred when updating hero: " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        LOGGER.info("Calling delete of HeroController");

        try {
            return ResponseEntity.ok(heroService.delete(id));
        } catch(IllegalArgumentException e) {
            LOGGER.error("Illegal Argument occurred when deleting hero: " + e.getMessage() );
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (HeroNotFoundException e) {
            LOGGER.error("A Hero with id : " + id + " not found when deleting hero");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("An internal error occurred when deleting hero: " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
