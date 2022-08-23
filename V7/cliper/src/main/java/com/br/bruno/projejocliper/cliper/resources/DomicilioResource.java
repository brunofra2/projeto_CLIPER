package com.br.bruno.projejocliper.cliper.resources;

import com.br.bruno.projejocliper.cliper.dtos.DomicilioDto;
import com.br.bruno.projejocliper.cliper.entities.Domicilio;
import com.br.bruno.projejocliper.cliper.entities.Usuario;
import com.br.bruno.projejocliper.cliper.services.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cliper/domiclio/")
public class DomicilioResource {

    @Autowired
    private DomicilioService domicilioService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<DomicilioDto>> findAll(){

        return  ResponseEntity.ok().body(domicilioService.findAll());
    }

    public ResponseEntity<DomicilioDto> findById(Integer id){

        return  ResponseEntity.ok().body(domicilioService.findById(id));
    }

    public ResponseEntity<DomicilioDto> create(Domicilio domicilio){

        return ResponseEntity.ok().body(domicilioService.create(domicilio));
    }

    public ResponseEntity<DomicilioDto> update(Domicilio domicilio){

        return ResponseEntity.ok().body(domicilioService.update(domicilio));

    }
    public ResponseEntity<?> delete(Integer id){
        domicilioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
