package com.consultorio.consultorio.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTurno;


    private LocalDateTime fechaHora;
    
    public Turno(){} //Constructor vacio para JPA

    //Base de datos Medico

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    //Base de datos Paciente

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Turno(LocalDateTime fechaHora, Medico medico, Paciente paciente) {
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
    }

    public long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(long idTurno) {
        this.idTurno = idTurno;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Turno [idTurno=" + idTurno + ", fechaHora=" + fechaHora + ", medico=" + medico + ", paciente="
                + paciente + "]";
    }
    
    
    


    
}
