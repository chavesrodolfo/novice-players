package com.github.chavesrodolfo.noviceplayers.repository;

import com.github.chavesrodolfo.noviceplayers.model.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
