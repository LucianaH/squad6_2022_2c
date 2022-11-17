package com.aninfo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recurso {

    @Id
    public Long legajo;
    public String Nombre;
    public String Apellido;

    public Recurso() {
    }

    public Recurso(Long legajo, String nombre, String apellido) {
        this.legajo = legajo;
        this.Nombre = nombre;
        this.Apellido = apellido;
    }

    @JsonProperty(value = "legajo")
    public Long getId() {
        return legajo;
    }

    @JsonProperty(value = "Nombre")
    public String getNombre() {
        return Nombre;
    }

    @JsonProperty(value = "Apellido")
    public String getApellido() {
        return Apellido;
    }
}
