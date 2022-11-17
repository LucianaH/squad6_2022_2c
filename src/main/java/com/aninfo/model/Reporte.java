package com.aninfo.model;

import com.aninfo.enums.Estado;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long reporteId;
    public Long legajo;
    public static int MAX_HORAS = 8;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(required = true, example = "2022-10-20")
    public LocalDate fecha;
    public Estado estado;
    public Long proyectoId;
    public Long  tareaId;
    public Long cantidadHoras;

    public static int getMaxHoras() {
        return MAX_HORAS;
    }

    public Reporte() {
    }

    public void setReporteId(Long id) {
        this.reporteId = id;
    }

    public Reporte(Estado estado, Long legajo, LocalDate fecha, Long proyectoId, Long tareaId, Long cantidadHoras) {
        this.estado = estado;
        this.legajo = legajo;
        this.fecha = fecha;
        this.proyectoId = proyectoId;
        this.tareaId = tareaId;
        this.cantidadHoras = cantidadHoras;
    }

    public Long getReporteId() {
        return reporteId;
    }

    public Long getLegajo() {
        return legajo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public Long getProyecto() {
        return proyectoId;
    }

    public Long getTarea() {
        return tareaId;
    }

    public Long getCantidadHoras() {
        return cantidadHoras;
    }
}