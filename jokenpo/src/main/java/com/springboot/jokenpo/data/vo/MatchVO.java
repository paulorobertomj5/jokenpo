package com.springboot.jokenpo.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "name"})
public class MatchVO extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private Long key;
    private String name;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchVO)) return false;
        if (!super.equals(o)) return false;
        MatchVO matchVO = (MatchVO) o;
        return getKey().equals(matchVO.getKey()) &&
                getName().equals(matchVO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getName());
    }
}
