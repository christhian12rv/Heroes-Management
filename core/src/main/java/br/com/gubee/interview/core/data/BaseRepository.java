package br.com.gubee.interview.core.data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface BaseRepository<T> extends Serializable {

    T findById(UUID id);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void delete(UUID id);
}
