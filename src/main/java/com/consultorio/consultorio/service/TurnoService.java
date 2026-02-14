package com.consultorio.consultorio.service;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.consultorio.consultorio.repository.TurnoRepository;
import com.consultorio.consultorio.model.Paciente;
import com.consultorio.consultorio.model.Medico;
import com.consultorio.consultorio.model.Turno;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository repoTurno;

    public boolean agregarTurno(LocalDateTime fechaHora, Medico medico, Paciente paciente){

        if(repoTurno.existsByMedicoAndFechaAndHora(medico, fechaHora)){
            throw new RuntimeException("El medico ya tiene un turno con esa fecha y hora");
        }

        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setMedico(medico);
        turno.setFechaHora(fechaHora);
        repoTurno.save(turno);

        repoTurno.save(turno);
        return true;
    }

    //Buscar turno por id
    public Turno buscarTurnoPorId(Long id){
       return repoTurno.findById(id).orElseThrow(() -> new RuntimeException("Turno no encontrado")); 
    }

    //Listar turnos por fechaHora
    public List<Turno> listar(LocalDateTime fechaHora){
       
        List<Turno> turno = repoTurno.findByFechaHora(fechaHora);

        if(turno.isEmpty()){
            throw new RuntimeException("No hay turnos con esa fecha y hora");
        }

        return turno;
    
    }

    //Listar todos los turnos
    public List<Turno> listar(){
        return repoTurno.findAll();
    }

    //Eliminar turno por id
    public boolean eliminarTurnoPorId(Long id) {
        if(repoTurno.findById(id).isEmpty()){
            throw new RuntimeException("Turno no encontrado");
        }

        repoTurno.deleteById(id);
        return true;
    }

    

}
