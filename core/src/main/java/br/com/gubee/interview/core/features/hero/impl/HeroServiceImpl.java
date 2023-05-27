package br.com.gubee.interview.core.features.hero.impl;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.HeroService;
import br.com.gubee.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    HeroRepository heroRepository;

    @Override
    public Hero findById(UUID id) {
        Assert.notNull(id, "Id não pode ser nulo");

        return heroRepository.findByPk(id);
    }

    @Override
    public Hero findByName(String name) {
        Assert.notNull(name, "Nome não pode ser nulo");

        return heroRepository.findByName(name);
    }

    @Override
    public void save(Hero hero) {
        Assert.notNull(hero, "Herói não pode ser nulo");

        heroRepository.save(hero);
    }
}
