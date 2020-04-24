package com.springboot.jokenpo.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="play")
public class Play implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "match_id",nullable = false)
    private Integer matchId;
    @Column(name= "player_id",nullable = false)
    private Integer playerId;
    @Column(nullable = false)
    private String played;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof Play)) return false;
        Play play = (Play) o;
        return getId().equals(play.getId()) &&
                getMatchId().equals(play.getMatchId()) &&
                getPlayerId().equals(play.getPlayerId()) &&
                getPlayed().equals(play.getPlayed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMatchId(), getPlayerId(), getPlayed());
    }
}
