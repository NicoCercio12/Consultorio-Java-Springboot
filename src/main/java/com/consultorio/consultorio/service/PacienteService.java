package com.consultorio.consultorio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.consultorio.consultorio.dto.PacienteRequestDTO;
import com.consultorio.consultorio.dto.PacienteResponseDTO;
import com.consultorio.consultorio.dto.PacienteUpdateDTO;
import com.consultorio.consultorio.model.Paciente;
import com.consultorio.consultorio.repository.PacienteRepository;
import java.util.List;


@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repoPaciente;

    //Agregar/Crear Paciente 

    public void agregarPaciente(PacienteRequestDTO dtoPaciente){ //Al parecer, es mejor hacerlo con void
        if(repoPaciente.findByDNI(dtoPaciente.getDni()).isPresent()){
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

    //Buscar Paciente por id

    public PacienteResponseDTO buscarPacientePorId(Long id){
        Paciente paciente = repoPaciente.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        return mapToDTO(paciente);
    }

    //Listar todos los pacientes

    public List<PacienteResponseDTO> listar(){ 
        return repoPaciente.findAll().stream().map(this::mapToDTO).toList();
            
    }

    //Modificar Paciente por id

    public void modificarPacientePorId(Long id, PacienteUpdateDTO dtoPaciente){

        Paciente paciente = repoPaciente.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if(dtoPaciente.getNombre() != null){ //Solo me aseguro de settear si no esta vacio (medio robusto con ifs, pero bueno)
            dtoPaciente.setNombre(paciente.getNombre());
        }

        if(dtoPaciente.getApellido() != null) {
            dtoPaciente.setApellido(paciente.getApellido());
        }

        if(dtoPaciente.getDni() != null){ //No creo usarlo, pero lo dejo qsyo :P
            dtoPaciente.setDni(paciente.getDni());
        }

        if(dtoPaciente.getNroTelefono() != null){
            dtoPaciente.setNroTelefono(paciente.getNroTelefono());
        }

        if(dtoPaciente.getFechaNacimiento() != null) {
            dtoPaciente.setFechaNacimiento(paciente.getFechaNacimiento()); //OBVIAMENTE lo hago por si el paciente se equivoca al ingresar su fecha de nacimiento
        }
        
        if(dtoPaciente.getObraSocial() != null){
            dtoPaciente.setObraSocial(paciente.getObraSocial());
        }

        repoPaciente.save(paciente);

    }

    //Eliminar Paciente por id

    public void eliminarPacientePorId(Long id){
        if(!repoPaciente.existsById(id)) { //Es mejor usar existsById negado si quiero asegurarme que no existe el paciente
            throw new RuntimeException("Paciente no encontrado");
        }

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

