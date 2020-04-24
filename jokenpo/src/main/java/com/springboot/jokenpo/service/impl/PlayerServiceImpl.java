package com.springboot.jokenpo.service.impl;

import com.springboot.jokenpo.converter.DozerConverter;
import com.springboot.jokenpo.data.model.Player;
import com.springboot.jokenpo.data.vo.PlayerVO;
import com.springboot.jokenpo.exception.ResourceNotFoundException;
import com.springboot.jokenpo.repository.PlayerRepository;
import com.springboot.jokenpo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository repository;

    public PlayerVO create(PlayerVO player) {
        var entity = DozerConverter.parseObject(player, Player.class);
        if(entity != null) {
            return DozerConverter.parseObject(repository.save(entity), PlayerVO.class);
        }else{
            return null;
        }
    }

    public List<PlayerVO> findByAll() {
        var list = repository.findAll();
        if(list != null && !list.isEmpty()) {
            return DozerConverter.parseListObject(repository.findAll(), PlayerVO.class);
        }else{
            return  null;
        }
    }

    public PlayerVO findById(Long id) {
        var entity = repository.findById(id);
        if(entity != null && !entity.isEmpty()) {
            return DozerConverter.parseObject(entity, PlayerVO.class);
        }else{
            return null;
        }
    }

    public PlayerVO update(PlayerVO player) {
        var entity = repository.findById(player.getKey()).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));

        entity.setFistName(player.getFistName());
        entity.setLastName(player.getLastName());
        entity.setAddress(player.getAddress());
        entity.setGender(player.getGender());
        if(entity != null) {
            return DozerConverter.parseObject(repository.save(entity), PlayerVO.class);
        }else{
            return null;
        }
    }

    public void delete(Long id) {
        Player entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));
        repository.delete(entity);
    }

}
