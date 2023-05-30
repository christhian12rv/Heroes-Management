package br.com.gubee.interview.core.features.hero.impl;

import br.com.gubee.interview.core.Exceptions.HeroNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.HeroService;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.UUID;

@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    HeroRepository heroRepository;

    @Autowired
    PowerStatsService powerStatsService;

    @Override
    public Hero findById(UUID id) {
        Assert.notNull(id, "Id é obrigatório");

        return heroRepository.findById(id);
    }

    @Override
    public Hero findByName(String name) {
        Assert.notNull(name, "Nome é obrigatório");

        return heroRepository.findByName(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Hero save(Hero hero) {
        Assert.notNull(hero, "O herói é obrigatório");
        Assert.notNull(hero.getPowerStats(), "Estatísticas de poder é obrigatória");

        heroRepository.save(hero);

        return hero;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Hero update(Hero hero) throws HeroNotFoundException {
        Assert.notNull(hero, "O herói não pode ser nulo");
        Assert.notNull(hero.getId(), "O id é obrigatório");

        Hero heroFound = heroRepository.findById(hero.getId());

        if (heroFound == null)
            throw new HeroNotFoundException("Herói não encontrado");

        heroFound.copyNonNullAttributes(hero);
        heroFound.getPowerStats().copyNonNullAttributes(hero.getPowerStats());

        heroRepository.update(heroFound);

        return hero;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Hero delete(UUID id) throws HeroNotFoundException {
        Assert.notNull(id, "O id é obrigatório");

        Hero heroFound = heroRepository.findById(id);

        if (heroFound == null)
            throw new HeroNotFoundException("Herói não encontrado");

        heroRepository.delete(id);

        return heroFound;
    }
}
