package com.springboot.jokenpo.service;

import com.springboot.jokenpo.data.vo.PlayerVO;

import java.util.List;


public interface PlayerService {


    PlayerVO create(PlayerVO person);

    List<PlayerVO> findByAll();

    PlayerVO findById(Long id);

    PlayerVO update(PlayerVO person);

    void delete(Long id);

}
