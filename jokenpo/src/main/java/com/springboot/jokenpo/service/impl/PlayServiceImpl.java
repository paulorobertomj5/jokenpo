package com.springboot.jokenpo.service.impl;

import com.springboot.jokenpo.converter.DozerConverter;
import com.springboot.jokenpo.data.model.Play;
import com.springboot.jokenpo.data.vo.PlayVO;
import com.springboot.jokenpo.exception.ResourceNotFoundException;
import com.springboot.jokenpo.repository.PlayCustomRepository;
import com.springboot.jokenpo.repository.PlayRepository;
import com.springboot.jokenpo.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayServiceImpl implements PlayService {

    @Autowired
    private PlayRepository repository;

    @Autowired
    private PlayCustomRepository playCustomRepository;

    public PlayVO create(PlayVO play) {
        var entity = DozerConverter.parseObject(play, Play.class);
        if(entity != null) {
            return DozerConverter.parseObject(repository.save(entity), PlayVO.class);
        }else{
            return null;
        }
    }

    public List<PlayVO> findByAll() {
        var list = repository.findAll();
        if(list != null && !list.isEmpty()) {
            return DozerConverter.parseListObject(repository.findAll(), PlayVO.class);
        }else{
            return  null;
        }
    }

    public PlayVO findById(Long id) {
        var entity = repository.findById(id);
        if(entity != null && !entity.isEmpty()) {
            return DozerConverter.parseObject(entity, PlayVO.class);
        }else{
            return null;
        }
    }

    @Override
    public List<PlayVO> findMatchById(Long id) {
        return playCustomRepository.findMatchById(id);
    }

    public PlayVO update(PlayVO play) {
        var entity = repository.findById(play.getKey()).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));

        entity.setMatchId(play.getMatchId());
        entity.setPlayerId(play.getPlayerId());
        entity.setPlayed(play.getPlayed());


        if(entity != null) {
            return DozerConverter.parseObject(repository.save(entity), PlayVO.class);
        }else{
            return null;
        }
    }

    public void delete(Long id) {
        Play entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));
        repository.delete(entity);
    }

}
