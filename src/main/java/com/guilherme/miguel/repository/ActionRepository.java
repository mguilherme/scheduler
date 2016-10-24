package com.guilherme.miguel.repository;

import com.guilherme.miguel.domain.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Miguel Guilherme
 */
public interface ActionRepository extends CrudRepository<Action, Integer> {

    @Transactional
    Long deleteByName(String name);
}
