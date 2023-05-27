package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.data.BaseRepository;
import br.com.gubee.interview.model.Hero;

public interface HeroRepository extends BaseRepository<Hero> {
    Hero findByName(String name);
}
