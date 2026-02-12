package com.consultorio.consultorio.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Paciente extends Persona {

    private LocalDate fechaNacimiento;
    private String obraSocial;

    public Paciente() {} //Constructor vacio para JPA
    
    public Paciente(int idPersona, String nombre, String apellido, long dni, String nroTelefono,
            LocalDate fechaNacimiento, String obraSocial) {
        super(nombre, apellido, dni, nroTelefono);
        this.fechaNacimiento = fechaNacimiento;
        this.obraSocial = obraSocial;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    @Override
    public String toString() {
        return "Paciente [idPersona=" + idPersona + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento
                + ", apellido=" + apellido + ", obraSocial=" + obraSocial + ", dni=" + dni + ", nroTelefono="
                + nroTelefono + "]";
    }

    
}
