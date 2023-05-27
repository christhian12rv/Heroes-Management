package br.com.gubee.interview.core.data.impl;

import br.com.gubee.interview.core.data.BaseRepository;
import org.hibernate.Session;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Repository
public class AbstractBaseRepository<T> implements BaseRepository<T> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractBaseRepository() {
        this.type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractBaseRepository.class);
    }

    public T findByPk(UUID pk) {
        T entity = entityManager.find(type, pk);

        return entity;
    }

    public List<T> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM " + type.getName() + " e");
        List<T> entitys = query.getResultList();

        return entitys;
    }

    public void update(Object entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void save(Object entity) {
        entityManager.persist(entity);
    }

    public void delete(Integer pk) {
        T entity = entityManager.find(type, pk);

        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}