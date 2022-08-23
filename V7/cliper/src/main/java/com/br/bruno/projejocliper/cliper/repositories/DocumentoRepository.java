package com.br.bruno.projejocliper.cliper.repositories;

import com.br.bruno.projejocliper.cliper.entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}