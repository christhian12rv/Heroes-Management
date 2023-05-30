package br.com.gubee.interview.core.features.powerstats.impl;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class PowerStatsServiceImpl implements PowerStatsService {
    @Autowired
    PowerStatsRepository powerStatsRepository;

    @Override
    public PowerStats save(PowerStats powerStats) {
        Assert.notNull(powerStats, "Estatísticas de poder é obrigatório");

        powerStatsRepository.save(powerStats);

        return powerStats;
    }
}
