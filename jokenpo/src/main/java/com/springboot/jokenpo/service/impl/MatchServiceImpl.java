package com.springboot.jokenpo.service.impl;

import com.springboot.jokenpo.converter.DozerConverter;
import com.springboot.jokenpo.data.model.Match;
import com.springboot.jokenpo.data.vo.MatchVO;
import com.springboot.jokenpo.data.vo.PlayVO;
import com.springboot.jokenpo.exception.ResourceNotFoundException;
import com.springboot.jokenpo.repository.MatchRepository;
import com.springboot.jokenpo.service.MatchService;
import com.springboot.jokenpo.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.groupingBy;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository repository;

    @Autowired
    private PlayService playService;

    public MatchVO create(MatchVO client) {
        var entity = DozerConverter.parseObject(client, Match.class);
        if (entity != null) {
            return DozerConverter.parseObject(repository.save(entity), MatchVO.class);
        } else {
            return null;
        }
    }

    public List<MatchVO> findByAll() {
        var list = repository.findAll();
        if (list != null && !list.isEmpty()) {
            return DozerConverter.parseListObject(repository.findAll(), MatchVO.class);
        } else {
            return null;
        }
    }

    public MatchVO findById(Long id) {
        var entity = repository.findById(id);
        if (entity != null && !entity.isEmpty()) {
            return DozerConverter.parseObject(entity, MatchVO.class);
        } else {
            return null;
        }
    }

    public MatchVO update(MatchVO client) {
        var entity = repository.findById(client.getKey()).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));

        if (entity != null) {

            entity.setName(client.getName());

            return DozerConverter.parseObject(repository.save(entity), MatchVO.class);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        Match entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));
        repository.delete(entity);
    }

    @Override
    public String findResultMatchById(Long id) {

        List<PlayVO> playList = DozerConverter.parseListObject(playService.findMatchById(id), PlayVO.class);
        String resultMatch = "";
        if (playList != null && !playList.isEmpty()) {
            Map<String, List<PlayVO>> jogadasGrouped = playList.stream()
                    .collect(groupingBy(PlayVO::getPlayed));

            String resultMessage = VerifyResult(id, jogadasGrouped);

            resultMatch = resultMessage;
        }
        return resultMatch;
    }

    private String VerifyResult(Long id, Map<String, List<PlayVO>> jogadasGrouped) {

        String paper = null;
        String rock = null;
        String scissors = null;
        AtomicReference<String> resultMessage = new AtomicReference<>("");

        for (Map.Entry<String, List<PlayVO>> map : jogadasGrouped.entrySet()) {
            if (map.getKey().equals("SCISSORS")) {
                scissors = "SCISSORS";
            }
            if (map.getKey().equals("PAPER")) {
                paper = "PAPER";
            }
            if (map.getKey().equals("ROCK")) {
                rock = "ROCK";
            }
        }

        if (paper != null && rock != null && scissors != null) {
            resultMessage.set("DRAW");
        } else {
            whoIsWinner(id, jogadasGrouped, paper, rock, scissors, resultMessage);
        }

        return resultMessage.toString();
    }

    private void whoIsWinner(Long id, Map<String, List<PlayVO>> jogadasGrouped, String paper, String rock, String scissors, AtomicReference<String> resultMessage) {

        if (scissors != null && rock != null) {
            jogadasGrouped.entrySet().stream().forEach(x -> {
                System.out.println("x.getKey() = " + x.getKey());
                if (x.getKey().equals("ROCK")) {
                    x.getValue().stream().forEach(jogarModel -> {
                        resultMessage.set(resultMessage.get() + jogarModel.getPlayerId() + " ");
                    });
                    resultMessage.set("Jogador número " + resultMessage.get() + "venceu a partida " + id);
                }
            });
        }

        if (scissors != null && paper != null) {
            jogadasGrouped.entrySet().stream().forEach(x -> {
                System.out.println("x.getKey() = " + x.getKey());
                if (x.getKey().equals("SCISSORS")) {
                    x.getValue().stream().forEach(jogarModel -> {
                        resultMessage.set(resultMessage.get() + jogarModel.getPlayerId() + " ");
                    });
                    resultMessage.set("Jogador número " + resultMessage.get() + "venceu a partida " + id);
                }
            });
        }

        if (rock != null && paper != null) {
            jogadasGrouped.entrySet().stream().forEach(x -> {
                System.out.println("x.getKey() = " + x.getKey());
                if (x.getKey().equals("PAPER")) {
                    x.getValue().stream().forEach(jogarModel -> {
                        resultMessage.set(resultMessage.get() + jogarModel.getPlayerId() + " ");
                    });
                    resultMessage.set("Jogador número " + resultMessage.get() + "venceu a partida " + id);
                }
            });
        }
    }
}
