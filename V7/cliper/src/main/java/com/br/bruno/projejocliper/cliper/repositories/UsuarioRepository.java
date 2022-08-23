package com.br.bruno.projejocliper.cliper.repositories;

import com.br.bruno.projejocliper.cliper.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}