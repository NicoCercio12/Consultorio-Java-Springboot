package com.consultorio.consultorio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.consultorio.consultorio.repository.TurnoRepository;
import com.consultorio.consultorio.model.Paciente;
import com.consultorio.consultorio.dto.TurnoRequestDTO;
import com.consultorio.consultorio.dto.TurnoResponseDTO;
import com.consultorio.consultorio.model.Medico;
import com.consultorio.consultorio.model.Turno;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository repoTurno;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    //Metodo para agregar turnos

    public void agregarTurno(TurnoRequestDTO dtoTurno){

        Medico medico = medicoService.buscarEntidadMedicoPorId(dtoTurno.getIdMedico());
        Paciente paciente = pacienteService.buscarEntidadPacientePorId(dtoTurno.getIdPaciente());

        if(repoTurno.existsByMedicoAndFechaHora(medico, dtoTurno.getFechaHora())){
            throw new RuntimeException("Ya existe un turno con estos datos");
        }

        Turno turno = new Turno();
        turno.setFechaHora(dtoTurno.getFechaHora());
        turno.setMedico(medico);
        turno.setPaciente(paciente);

    }

    //Buscar entidad turno por id

    public Turno buscarEntidadTurnoPorId(Long id){
        return repoTurno.findById(id).orElseThrow(() -> new RuntimeException("Turno no encontrado"));
    }

    //Buscar turno por id

    public TurnoResponseDTO buscarTurnoPorId(Long id){
        Turno turno = buscarEntidadTurnoPorId(id);

        return mapToDTO(turno);
    }

    //Listar turnos

    public List<TurnoResponseDTO> listar(){
        return repoTurno.findAll().stream().map(this::mapToDTO).toList();
    }

    //Modificar turno por id

    public void modificarTurnoPorId(Long id, TurnoRequestDTO dtoTurno){

        Turno turno = repoTurno.findById(id).orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        if(dtoTurno.getFechaHora() != null){
            turno.setFechaHora(dtoTurno.getFechaHora());
        }

        if(dtoTurno.getIdMedico()!= null) {
            Medico medico = medicoService.buscarEntidadMedicoPorId(dtoTurno.getIdMedico());
            turno.setMedico(medico);
        }

        if(turno.getPaciente() != null){
            Paciente paciente = pacienteService.buscarEntidadPacientePorId(dtoTurno.getIdPaciente());
            turno.setPaciente(paciente);
        }

        repoTurno.save(turno);

    }

    //Eliminar turno por id

    public void eliminarTurnoPorId(Long id){
        if(!repoTurno.existsById(id)){
            throw new RuntimeException("Turno no encontrado");
        }

        repoTurno.deleteById(id);
    }


    //Mapper

    private TurnoResponseDTO mapToDTO(Turno turno) {

        TurnoResponseDTO dtoTurno = new TurnoResponseDTO();
        dtoTurno.setIdTurno(turno.getIdTurno());
        dtoTurno.setFechaHora(turno.getFechaHora());
        dtoTurno.setIdMedico(turno.getMedico().getIdPersona());
        dtoTurno.setIdPaciente(turno.getPaciente().getIdPersona());
        
        return dtoTurno;

    }
    

    

}
