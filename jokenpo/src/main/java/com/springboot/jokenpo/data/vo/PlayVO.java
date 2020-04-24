package com.springboot.jokenpo.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Objects;


public class PlayVO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private Long key;
    private Integer matchId;
    private Integer playerId;
    private String played;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayed() {
        return played;
    }

    public void setPlayed(String played) {
        this.played = played;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayVO)) return false;
        if (!super.equals(o)) return false;
        PlayVO playVO = (PlayVO) o;
        return getKey().equals(playVO.getKey()) &&
                getMatchId().equals(playVO.getMatchId()) &&
                getPlayerId().equals(playVO.getPlayerId()) &&
                getPlayed().equals(playVO.getPlayed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getMatchId(), getPlayerId(), getPlayed());
    }
}
