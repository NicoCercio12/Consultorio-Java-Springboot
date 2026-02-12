package com.consultorio.consultorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultorio.consultorio.model.Medico;
import com.consultorio.consultorio.repository.MedicoRepository;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository repoMedico;

    public boolean agregarMedico(Medico medico){
        if(repoMedico.findfindByMatricula(medico.getMatricula()).isPresent()){
            throw new RuntimeException("Ya existe el medico");
        }

        repoMedico.save(medico);
        return true;

    }

    public List<Medico> listar(){
        return repoMedico.findAll();
    }

}
