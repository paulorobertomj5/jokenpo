package com.springboot.jokenpo.repository.impl;

import com.springboot.jokenpo.data.model.Play;
import com.springboot.jokenpo.data.vo.PlayVO;
import com.springboot.jokenpo.repository.PlayCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PlayRepositoryImpl implements PlayCustomRepository {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<PlayVO> findMatchById(Long id) {
        Query query = entityManager.createNativeQuery("SELECT p.* FROM play p WHERE p.match_id = ?", Play.class);
        query.setParameter(1, id);
        return query.getResultList();
    }
}
