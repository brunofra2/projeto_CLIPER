package com.br.bruno.projejocliper.cliper.resources;

import com.br.bruno.projejocliper.cliper.dtos.DomicilioDto;
import com.br.bruno.projejocliper.cliper.entities.Domicilio;
import com.br.bruno.projejocliper.cliper.entities.Usuario;
import com.br.bruno.projejocliper.cliper.services.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cliper/domicilio/")
public class DomicilioResource {

    @Autowired
    private DomicilioService domicilioService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<DomicilioDto>> findAll(){

        return  ResponseEntity.ok().body(domicilioService.findAll());
    }
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<DomicilioDto> findById(@PathVariable Integer id){

        return  ResponseEntity.ok().body(domicilioService.findById(id));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<DomicilioDto> create( @RequestBody Domicilio domicilio){

        return ResponseEntity.ok().body(domicilioService.create(domicilio));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<DomicilioDto> update(@RequestBody Domicilio domicilio){

        return ResponseEntity.ok().body(domicilioService.update(domicilio));

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        domicilioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
