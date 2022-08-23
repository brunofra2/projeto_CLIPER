package com.br.bruno.projejocliper.cliper.services;

import com.br.bruno.projejocliper.cliper.dtos.DomicilioDto;
import com.br.bruno.projejocliper.cliper.entities.Domicilio;
import com.br.bruno.projejocliper.cliper.exceptions.ResourceNotFoundException;
import com.br.bruno.projejocliper.cliper.mapper.DozerMapper;
import com.br.bruno.projejocliper.cliper.repositories.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;

    public List<DomicilioDto> findAll(){
        return DozerMapper.parseListObjects(domicilioRepository.findAll(),DomicilioDto.class);
    }

    public DomicilioDto findById(Integer id){
        var entity = domicilioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("domicilio inexistente"));
        return DozerMapper.parseObject(entity,DomicilioDto.class);

    }

    public DomicilioDto create(Domicilio domicilio){
        var entity = DozerMapper.parseObject(domicilio,Domicilio.class);
        var vo = DozerMapper.parseObject(domicilioRepository.save(entity),DomicilioDto.class);
        return vo;
    }

    public DomicilioDto update(Domicilio domicilio){
        var entity = domicilioRepository.findById(domicilio.getIdDomicilio())
                .orElseThrow(()-> new ResourceNotFoundException("domicilio inexistente"));
        entity.setCnae(domicilio.getCnae());
        entity.setIdDomicilio(domicilio.getIdDomicilio());
        entity.setCnpj(domicilio.getCnpj());
        entity.setNome(domicilio.getNome());
        entity.setFuncaoList(domicilio.getFuncaoList());
        entity.setLotacaoList(domicilio.getLotacaoList());
        var vo = DozerMapper.parseObject(domicilioRepository.save(entity),DomicilioDto.class);
        return vo;
    }

    public void delete(Integer id){

        var entity = domicilioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("domicilio inexistente"));
        domicilioRepository.delete(entity);

    }
}
