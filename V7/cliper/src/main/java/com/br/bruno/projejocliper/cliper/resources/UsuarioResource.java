package com.br.bruno.projejocliper.cliper.resources;

import com.br.bruno.projejocliper.cliper.dtos.UsuarioDto;
import com.br.bruno.projejocliper.cliper.entities.Usuario;
import com.br.bruno.projejocliper.cliper.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cliper/usuario/")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<UsuarioDto>> findAll(){
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id){
            return ResponseEntity.ok().body(usuarioService.findById(id));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<UsuarioDto> create(@RequestBody Usuario usuario){
            return ResponseEntity.ok().body(usuarioService.create(usuario));
    }
    @PutMapping(value = "/update")
    public ResponseEntity<UsuarioDto> update(@RequestBody Usuario usuario){
            return ResponseEntity.ok().body(usuarioService.update(usuario));
    }

    @DeleteMapping(value = "/delete/{id}")

    public ResponseEntity<?> delete(@PathVariable Long id){

        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
