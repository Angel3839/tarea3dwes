package com.Angelvf3839.tarea3dwesangel.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ejemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String codigoPlanta;

    public Ejemplar() {
    }

    public Ejemplar(long id, String nombre, String codigoPlanta) {
        this.id = id;
        this.nombre = nombre;
        this.codigoPlanta = codigoPlanta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoPlanta() {
        return codigoPlanta;
    }

    public void setCodigoPlanta(String codigoPlanta) {
        this.codigoPlanta = codigoPlanta;
    }

    @Override
    public String toString() {
        return "Id de ejemplar: " + this.id +
               "\nNombre de ejemplar: " + this.nombre +
               "\nCódigo de planta: " + this.codigoPlanta;
    }
}
