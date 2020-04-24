package com.springboot.jokenpo.repository;

import com.springboot.jokenpo.data.model.Play;
import com.springboot.jokenpo.data.vo.PlayVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PlayCustomRepository {
    List<PlayVO> findMatchById(Long id);
}
