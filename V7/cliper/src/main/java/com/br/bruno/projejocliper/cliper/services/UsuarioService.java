package com.br.bruno.projejocliper.cliper.services;

import com.br.bruno.projejocliper.cliper.dtos.UsuarioDto;
import com.br.bruno.projejocliper.cliper.entities.Usuario;
import com.br.bruno.projejocliper.cliper.exceptions.ResourceNotFoundException;
import com.br.bruno.projejocliper.cliper.mapper.DozerMapper;
import com.br.bruno.projejocliper.cliper.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDto> findAll(){

        return DozerMapper.parseListObjects(usuarioRepository.findAll(), UsuarioDto.class);

    }

    public UsuarioDto findById(Long id){

        var entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario inexistente"));
        return DozerMapper.parseObject(entity, UsuarioDto.class);

    }

    public UsuarioDto create(Usuario usuario){
        var entity = DozerMapper.parseObject(usuario, Usuario.class);
        var vo = DozerMapper.parseObject(usuarioRepository.save(entity),UsuarioDto.class);

        return vo;

    }

    public UsuarioDto update(Usuario usuario){
        var entity = usuarioRepository.findById(usuario.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Usuario inexistente"));

        entity.setId(usuario.getId());
        entity.setCodigo(usuario.getCodigo());
        entity.setTipo(usuario.getTipo());
        entity.setSenha(usuario.getSenha());
        entity.setBackupList(usuario.getBackupList());
        entity.setTreinadorList(usuario.getTreinadorList());
        var vo = DozerMapper.parseObject(usuarioRepository.save(entity),UsuarioDto.class);
        return  vo;
    }

    public void delete(Long id){

        var entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario inexistente"));
        usuarioRepository.delete(entity);


    }
}
