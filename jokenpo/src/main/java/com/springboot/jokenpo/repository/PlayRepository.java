package com.springboot.jokenpo.repository;

import com.springboot.jokenpo.data.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends JpaRepository<Play, Long> {

}
