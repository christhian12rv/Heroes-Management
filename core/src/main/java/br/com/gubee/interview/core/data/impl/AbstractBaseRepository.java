package br.com.gubee.interview.core.data.impl;

import br.com.gubee.interview.core.data.BaseRepository;
import org.hibernate.Session;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public T findById(UUID id) {
        Query query = entityManager.createQuery("SELECT e FROM " + type.getName() + " e WHERE e.id = :id");
        query.setParameter("id", id);

        try {
            T entity = (T) query.getSingleResult();
            return entity;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<T> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM " + type.getName() + " e");
        List<T> entitys = query.getResultList();

        return entitys;
    }

    public void update(Object entity) {
        entityManager.merge(entity);
    }

    @Transactional
    public void save(Object entity) {
        entityManager.persist(entity);
    }

    public void delete(UUID id) {
        T entity = entityManager.find(type, id);

        entityManager.remove(entity);
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