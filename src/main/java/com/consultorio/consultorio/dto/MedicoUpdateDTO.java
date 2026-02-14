package com.consultorio.consultorio.dto;

//Actualizo medicos

public class MedicoUpdateDTO {
    
    private Long id;
    private String nombre;
    private String apellido;
    private Long dni;
    private String nroTelefono;
    private String matricula;
    private String especialidad;
   
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
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
