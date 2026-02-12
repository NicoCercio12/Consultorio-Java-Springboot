package com.consultorio.consultorio.service;

import java.time.LocalDateTime;

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

    public boolean crearTurno(Paciente paciente, Medico medico, LocalDateTime fechaHora){

        if(repoTurno.existsByMedicoAndFechaAndHora(medico, fechaHora)){
            throw new RuntimeException("El medico ya tiene un turno con esa fecha y hora");
        }

        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setMedico(medico);
        turno.setFechaHora(fechaHora);
        repoTurno.save(turno);

        return true;
    }
    
}
