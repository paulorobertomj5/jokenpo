package com.springboot.jokenpo.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="player")
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "first_name", nullable = false, length = 80)
    private String fistName;
    @Column(name= "last_name", nullable = false, length = 80)
    private String lastName;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 6)
    private String gender;

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player person = (Player) o;
        return getId().equals(person.getId()) &&
                getFistName().equals(person.getFistName()) &&
                getLastName().equals(person.getLastName()) &&
                getAddress().equals(person.getAddress()) &&
                getGender().equals(person.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFistName(), getLastName(), getAddress(), getGender());
    }
}
