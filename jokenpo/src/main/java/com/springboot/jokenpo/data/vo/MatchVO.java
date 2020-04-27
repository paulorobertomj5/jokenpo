package com.springboot.jokenpo.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "name"})
public class MatchVO extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private Long key;
    @NotNull
    @NotBlank
    private String name;

    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MatchVO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchVO)) return false;
        if (!super.equals(o)) return false;
        MatchVO matchVO = (MatchVO) o;
        return Objects.equals(getKey(), matchVO.getKey()) &&
                Objects.equals(getName(), matchVO.getName()) &&
                Objects.equals(message, matchVO.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getName(), message);
    }

    @Override
    public String toString() {
        return "MatchVO{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
