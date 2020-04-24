package com.springboot.jokenpo.service;

import com.springboot.jokenpo.data.vo.MatchVO;

import java.util.List;


public interface MatchService {


    MatchVO create(MatchVO person);

    List<MatchVO> findByAll();

    MatchVO findById(Long id);

    MatchVO update(MatchVO person);

    void delete(Long id);

    String findResultMatchById(Long id);
}
