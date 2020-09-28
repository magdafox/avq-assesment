package com.assesment.avaloq.repository;

import com.assesment.avaloq.domain.RollConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RollConfigurationRepository extends JpaRepository<RollConfiguration, Long> {
    Optional<RollConfiguration> findByDiceNumberAndDiceSide(Integer diceNumber, Integer diceSide);
}
