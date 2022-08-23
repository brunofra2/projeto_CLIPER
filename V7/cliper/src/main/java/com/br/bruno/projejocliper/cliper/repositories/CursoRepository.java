package com.br.bruno.projejocliper.cliper.repositories;

import com.br.bruno.projejocliper.cliper.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}