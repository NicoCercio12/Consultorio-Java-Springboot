package com.consultorio.consultorio.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Necesario para la generacion de Id
    protected long idPersona;

    protected String nombre;
    protected String apellido;
    protected long dni;
    protected String nroTelefono;

    public Persona(){} //Constructor vacio para JPA
   
    public Persona(String nombre, String apellido, long dni, String nroTelefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nroTelefono = nroTelefono;
    }

    public long getIdPersona() {
        return idPersona;
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

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    @Override
    public String toString() {
        return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
                + ", nroTelefono=" + nroTelefono + "]";
    }

    

    
    
}
