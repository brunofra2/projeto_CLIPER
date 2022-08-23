package com.br.bruno.projejocliper.cliper.repositories;

import com.br.bruno.projejocliper.cliper.entities.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
}