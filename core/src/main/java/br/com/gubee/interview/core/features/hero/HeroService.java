package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;

import java.util.UUID;

public interface HeroService {
    Hero findById(UUID id);

    Hero findByName(String name);

    void save(Hero hero);
}
