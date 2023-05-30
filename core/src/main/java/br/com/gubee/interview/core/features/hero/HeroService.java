package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.Exceptions.HeroNotFoundException;
import br.com.gubee.interview.model.Hero;

import java.util.UUID;

public interface HeroService {
    Hero findById(UUID id);

    Hero findByName(String name);

    Hero save(Hero hero);

    Hero update(Hero hero) throws HeroNotFoundException;

    Hero delete(UUID id) throws HeroNotFoundException;
}
