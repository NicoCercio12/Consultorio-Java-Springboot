package com.consultorio.consultorio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.consultorio.consultorio.model.Paciente;
import com.consultorio.consultorio.repository.PacienteRepository;


@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repoPaciente;

    public boolean agregarPaciente(Paciente paciente){
        if(repoPaciente.findfindByDNI(paciente.getDni()).isPresent()){
            throw new RuntimeException("El paciente ya existe");
        }

        repoPaciente.save(paciente);
        return true;

    }
}
