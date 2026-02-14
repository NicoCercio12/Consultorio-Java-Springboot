package com.consultorio.consultorio.dto;

import java.time.LocalDate;

//Lo uso para actualizar pacientes

public class PacienteUpdateDTO {

    private String nombre;
    private String apellido;
    private Long dni;
    private String nroTelefono;
    private LocalDate fechaNacimiento;
    private String obraSocial;
   
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

    
    
    
}
