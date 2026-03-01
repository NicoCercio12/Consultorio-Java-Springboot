package com.consultorio.consultorio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.consultorio.consultorio.dto.PacienteRequestDTO;
import com.consultorio.consultorio.dto.PacienteResponseDTO;
import com.consultorio.consultorio.model.Paciente;
import com.consultorio.consultorio.model.Turno;
import com.consultorio.consultorio.repository.PacienteRepository;
import com.consultorio.consultorio.repository.TurnoRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository repoPaciente;

    @Autowired
    private TurnoRepository repoTurno;

    //Agregar/Crear Paciente 

    public void agregarPaciente(PacienteRequestDTO dtoPaciente){ //Al parecer, es mejor hacerlo con void
        if(repoPaciente.findByDni(dtoPaciente.getDni()).isPresent()){
            throw new RuntimeException("El paciente ya existe");
        }

        //Creamos el paciente
        Paciente paciente = new Paciente();
        paciente.setNombre(dtoPaciente.getNombre());
        paciente.setApellido(dtoPaciente.getApellido());
        paciente.setDni(dtoPaciente.getDni());
        paciente.setNroTelefono(dtoPaciente.getNroTelefono());
        paciente.setFechaNacimiento(dtoPaciente.getFechaNacimiento());
        paciente.setObraSocial(dtoPaciente.getObraSocial());

        repoPaciente.save(paciente);

    }

    //Buscar entidad paciente por id

    public Paciente buscarEntidadPacientePorId(Long id){
        return repoPaciente.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    //Buscar Paciente por id

    public PacienteResponseDTO buscarPacientePorId(Long id){
        Paciente paciente = buscarEntidadPacientePorId(id);

        return mapToDTO(paciente);
    }

    //Listar todos los pacientes

    public List<PacienteResponseDTO> listar(){ 
        return repoPaciente.findAll().stream().map(this::mapToDTO).toList();
            
    }

    //Modificar Paciente por id

    public void modificarPacientePorId(PacienteRequestDTO dtoPaciente, Long id){

        Paciente paciente = repoPaciente.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if(dtoPaciente.getNombre() != null){ //Solo me aseguro de settear si no esta vacio (medio robusto con ifs, pero bueno)
            paciente.setNombre(dtoPaciente.getNombre());
        }

        if(dtoPaciente.getApellido() != null) {
            paciente.setApellido(dtoPaciente.getApellido());
        }

        if(dtoPaciente.getDni() != null){ //No creo usarlo, pero lo dejo qsyo :P
            paciente.setDni(dtoPaciente.getDni());
        }

        if(dtoPaciente.getNroTelefono() != null){
            paciente.setNroTelefono(dtoPaciente.getNroTelefono());
        }

        if(dtoPaciente.getFechaNacimiento() != null) {
            paciente.setFechaNacimiento(dtoPaciente.getFechaNacimiento()); //OBVIAMENTE lo hago por si el paciente se equivoca al ingresar su fecha de nacimiento
        }
        
        if(dtoPaciente.getObraSocial() != null){
            paciente.setObraSocial(dtoPaciente.getObraSocial());
        }

        repoPaciente.save(paciente);
    }

    //Eliminar Paciente por id

    public void eliminarPacientePorId(Long id){
        if(!repoPaciente.existsById(id)) { //Es mejor usar existsById negado si quiero asegurarme que no existe el paciente
            throw new RuntimeException("Paciente no encontrado");
        }

        //Limpio turnos para evitar bugs

        List<Turno> turnoPaciente = repoTurno.findAll().stream()
        .filter(t -> t.getPaciente() != null && t.getPaciente().getIdPersona() == id)
        .collect(Collectors.toList());
        repoTurno.deleteAll(turnoPaciente);


        repoPaciente.deleteById(id); //Elimino al paciente
    }

    //Mapper

    private PacienteResponseDTO mapToDTO(Paciente paciente){
        PacienteResponseDTO dtoPaciente = new PacienteResponseDTO();

        dtoPaciente.setId(paciente.getIdPersona());
        dtoPaciente.setNombre(paciente.getNombre());
        dtoPaciente.setApellido(paciente.getApellido());
        dtoPaciente.setDni(paciente.getDni());
        dtoPaciente.setNroTelefono(paciente.getNroTelefono());
        dtoPaciente.setFechaNacimiento(paciente.getFechaNacimiento());
        dtoPaciente.setObraSocial(paciente.getObraSocial());

        return dtoPaciente;
    }
    
}

