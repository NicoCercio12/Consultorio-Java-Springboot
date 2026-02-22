package com.consultorio.consultorio.dto;

import org.antlr.v4.runtime.misc.NotNull;

//Utilizado para crear medicos

public class MedicoRequestDTO {

    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private Long dni;
    private String nroTelefono;
    @NotNull
    private String matricula;
    @NotNull
    private String especialidad;
   
    public MedicoRequestDTO(){} //Me habia olvidado de agregarlo

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public String getNroTelefono() {
        return nroTelefono;
    }
    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
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

    




    
}
