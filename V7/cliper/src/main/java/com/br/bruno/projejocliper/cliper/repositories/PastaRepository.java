package com.br.bruno.projejocliper.cliper.repositories;

import com.br.bruno.projejocliper.cliper.entities.Pasta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastaRepository extends JpaRepository<Pasta, Long> {
}