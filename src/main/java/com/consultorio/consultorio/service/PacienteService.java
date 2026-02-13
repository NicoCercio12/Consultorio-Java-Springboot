package com.consultorio.consultorio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.consultorio.consultorio.model.Paciente;
import com.consultorio.consultorio.repository.PacienteRepository;
import java.util.List;


@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repoPaciente;

    public boolean agregarPaciente(Paciente paciente){ //Crear Paciente
        if(repoPaciente.findByDNI(paciente.getDni()).isPresent()){
            throw new RuntimeException("El paciente ya existe");
        }

        repoPaciente.save(paciente);
        return true;

    }

    public Paciente buscarPacientePorDni(Long dni){
        return repoPaciente.findByDNI(dni).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        //Implementar si esta activo o no

    }

    public Paciente buscarPacientePorId(Long id){
        return repoPaciente.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public List<Paciente> listar(){ //Necesario para listar a todos los pacientes
        return repoPaciente.findAll();
            
    }

    
}

