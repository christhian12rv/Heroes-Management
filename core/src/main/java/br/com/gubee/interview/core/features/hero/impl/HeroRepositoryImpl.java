package br.com.gubee.interview.core.features.hero.impl;

import br.com.gubee.interview.core.data.impl.AbstractBaseRepository;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.model.Hero;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class HeroRepositoryImpl extends AbstractBaseRepository<Hero> implements HeroRepository {

    @Override
    public Hero findByName(String name) {
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT h.* ");
        sql.append(" FROM hero h ");
        sql.append(" WHERE h.name = :name ");

        SQLQuery query = getSession().createSQLQuery(sql.toString());
        query.setString("name", name);
        query.addEntity(Hero.class);

        try {
            Hero hero = (Hero) query.uniqueResult();
            return hero;
        } catch (NoResultException e) {
            return null;
        }
    }
}
