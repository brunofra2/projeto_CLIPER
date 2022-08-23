package com.br.bruno.projejocliper.cliper.resources;

import com.br.bruno.projejocliper.cliper.services.AtividadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cliper/atividades/")
public class AtividadesResourse {

    @Autowired
    private AtividadesService atividadesService;

    @GetMapping("/teste")
    public ResponseEntity<?> teste(){
        System.out.println("teste");
        return ResponseEntity.ok().body("teste");
    }
    @GetMapping("/all")
    public ResponseEntity<List<?>> findAll(){
        return ResponseEntity.ok().body(atividadesService.findAll());
    }
}
