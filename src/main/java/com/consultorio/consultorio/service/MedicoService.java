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
        if(repoMedico.findByMatricula(medico.getMatricula()).isPresent()){
            throw new RuntimeException("Ya existe el medico");
        }

        repoMedico.save(medico);
        return true;

    }

    public Medico traerMedicoPorMatricula(String matricula){
        return repoMedico.findByMatricula(matricula).orElseThrow(() -> new RuntimeException("Medico no encontrado"));
    }

    public Medico BuscarMedicoPorId(Long id) {
        return repoMedico.findById(id).orElseThrow(() -> new RuntimeException("Medico no econtrado"));

    }

    public Medico buscarMedicoPorDni(Long dni){
        return repoMedico.findByDni(dni).orElseThrow(() -> new RuntimeException("Medico no encontrado"));
    }

    public List<Medico> listarPorEspecialidad(String especialidad){
        
        List<Medico> medicoEspecialidad = repoMedico.findByEspecialidad(especialidad);

        if(medicoEspecialidad.isEmpty()){
            throw new RuntimeException("No hay medicos con esa especialidad");
        }

        return medicoEspecialidad;

    }

    public List<Medico> listar(){
        return repoMedico.findAll();
    }

    //Puse tres opciones para actualizar (no se que tan necesario sea, pero aun asi :p)

    public Medico actualizarMedicoPorMatricula(String matricula, Medico medicoActualizar){
       
       Medico medico = repoMedico.findByMatricula(matricula).orElseThrow(() -> new RuntimeException("Medico no encontrado"));

       medico.setNombre(medicoActualizar.getNombre());
       medico.setApellido(medicoActualizar.getApellido());
       medico.setEspecialidad(medicoActualizar.getEspecialidad());
       medico.setNroTelefono(medicoActualizar.getEspecialidad());

       return repoMedico.save(medico);

    }

    public Medico actualizarMedicoPorDni(Long dni, Medico medicoActualizar) {

        Medico medico = repoMedico.findByDni(dni).orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        medico.setNombre(medicoActualizar.getNombre());
        medico.setApellido(medicoActualizar.getApellido());
        medico.setEspecialidad(medicoActualizar.getEspecialidad());
        medico.setNroTelefono(medicoActualizar.getNroTelefono());

        return repoMedico.save(medico);
    }

    public Medico actualizarMedicoPorId(Long id, Medico medicoActualizar){

        Medico medico = repoMedico.findById(id).orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        medico.setNombre(medicoActualizar.getNombre());
        medico.setApellido(medicoActualizar.getApellido());
        medico.setEspecialidad(medicoActualizar.getEspecialidad());
        medico.setNroTelefono(medicoActualizar.getNroTelefono());

        return repoMedico.save(medico);
    }



}
