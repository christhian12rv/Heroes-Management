package br.com.gubee.interview.core.features.powerstats.impl;

import br.com.gubee.interview.core.data.impl.AbstractBaseRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.PowerStats;
import org.springframework.stereotype.Repository;

@Repository
public class PowerStatsRepositoryImpl extends AbstractBaseRepository<PowerStats> implements PowerStatsRepository {
}
