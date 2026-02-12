package com.consultorio.consultorio.model;

import jakarta.persistence.Entity;

@Entity
public class Medico extends Persona  {

    private String matricula;
    private String especialidad;

    public Medico() {} //Constructor vacio para JPA
    
    public Medico(String nombre, String apellido, long dni, String nroTelefono, String matricula,
            String especialidad) {
        super(nombre, apellido, dni, nroTelefono);
        this.matricula = matricula;
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Medico [matricula=" + matricula + ", idPersona=" + idPersona + ", especialidad=" + especialidad
                + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", nroTelefono=" + nroTelefono
                + "]";
    }

    

    
    
}
