package com.aninfo.repository;

import com.aninfo.model.Reporte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface ReporteRepository extends CrudRepository<Reporte, Long> {

    @Override
    List<Reporte> findAll();
    List<Reporte> findReporteByLegajo(Long legajo);
    List<Reporte> findReporteByProyectoId(Long proyectoId);
    List<Reporte> findReporteByTareaId(Long tareaId);
    List<Reporte> findReporteByFechaBetween(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fin);
}