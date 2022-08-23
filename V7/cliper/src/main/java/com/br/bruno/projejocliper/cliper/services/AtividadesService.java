package com.br.bruno.projejocliper.cliper.services;

import com.br.bruno.projejocliper.cliper.repositories.AtividadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadesService {

    @Autowired
    private AtividadesRepository atividadesRepository;

    public List<?> findAll(){
      return atividadesRepository.findAll();
    }

}
