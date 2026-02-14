package com.consultorio.consultorio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultorio.consultorio.dto.MedicoRequestDTO;
import com.consultorio.consultorio.dto.MedicoResponseDTO;
import com.consultorio.consultorio.model.Medico;
import com.consultorio.consultorio.repository.MedicoRepository;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository repoMedico;

   //Crear medico

   public void agregarMedico(MedicoRequestDTO dtoMedico) {

    if(repoMedico.findByDni(dtoMedico.getDni()).isPresent()){
        throw new RuntimeException("El medico ya existe");
    }

    Medico medico = new Medico();
    medico.setNombre(dtoMedico.getNombre());
    medico.setApellido(dtoMedico.getApellido());
    medico.setDni(dtoMedico.getDni());
    medico.setNroTelefono(dtoMedico.getNroTelefono());
    medico.setMatricula(dtoMedico.getMatricula());
    medico.setEspecialidad(dtoMedico.getEspecialidad());

    repoMedico.save(medico);

   }

   //Buscar medico por id 

   public MedicoResponseDTO buscarMedicoPorId(Long id){

    Medico medico = repoMedico.findById(id).orElseThrow(() -> new RuntimeException("Medico no encontrado"));

    return mapToDTO(medico);
   
   }

   //Listar todos los medicos

   public List<MedicoResponseDTO> listar(){
    return repoMedico.findAll().stream().map(this::mapToDTO).toList();
   }

   //Modificar medico

   public void modificarMedicoPorId(MedicoRequestDTO dtoMedico, Long id) {

    Medico medico = repoMedico.findById(id).orElseThrow(() -> new RuntimeException("Medico no encontrado"));

    //Me aseguro de que no esten estos campos vacíos (Recordar buscar una forma menos robusta de hacerlo)

    if(dtoMedico.getNombre() != null){
        dtoMedico.setNombre(medico.getNombre());
    }

    if(dtoMedico.getApellido() != null){
        dtoMedico.setApellido(medico.getApellido());
    }

    if(dtoMedico.getDni() != null){
        dtoMedico.setDni(medico.getDni());
    }

    if(dtoMedico.getNroTelefono() != null) {
        dtoMedico.setNroTelefono(medico.getNroTelefono());
    }

    if(dtoMedico.getMatricula() != null){
        dtoMedico.setMatricula(medico.getMatricula());
    }

    if(dtoMedico.getEspecialidad() != null){
        dtoMedico.setEspecialidad(medico.getEspecialidad());
    }

    repoMedico.save(medico);

   }

   //Mapper

   private MedicoResponseDTO mapToDTO(Medico medico){

    MedicoResponseDTO dtoMedico = new MedicoResponseDTO();

    dtoMedico.setId(medico.getIdPersona());
    dtoMedico.setNombre(medico.getNombre());
    dtoMedico.setApellido(medico.getApellido());
    dtoMedico.setDni(medico.getDni());
    dtoMedico.setNroTelefono(medico.getNroTelefono());
    dtoMedico.setMatricula(medico.getMatricula());
    dtoMedico.setEspecialidad(medico.getEspecialidad());

    return dtoMedico;

   }




}
