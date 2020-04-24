package com.springboot.jokenpo.service;

import com.springboot.jokenpo.data.vo.PlayVO;

import java.util.List;

public interface PlayService {

    PlayVO create(PlayVO play);

    List<PlayVO> findByAll();

    PlayVO findById(Long id);

    List<PlayVO> findMatchById(Long id);


    PlayVO update(PlayVO play);

    void delete(Long id);

}
