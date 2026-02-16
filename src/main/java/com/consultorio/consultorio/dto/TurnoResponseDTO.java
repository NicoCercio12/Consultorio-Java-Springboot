package com.consultorio.consultorio.dto;

import java.time.LocalDateTime;

public class TurnoResponseDTO {

    private Long idTurno;
    private LocalDateTime fechaHora;
    private Long idMedico;
    private Long idPaciente;

    public Long getIdTurno() {
        return idTurno;
    }
    
    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    public Long getIdMedico() {
        return idMedico;
    }
    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }
    public Long getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
  
    
}
