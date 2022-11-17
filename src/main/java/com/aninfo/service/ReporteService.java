package com.aninfo.service;

import com.aninfo.exceptions.InvalidDateRangeException;
import com.aninfo.exceptions.InvalidHoursException;
import com.aninfo.exceptions.InvalidLegajoException;
import com.aninfo.model.Recurso;
import com.aninfo.model.Reporte;
import com.aninfo.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;
    @Autowired
    private RecursoService recursoService;

    private void checkReporteValido(Reporte reporte) {
        if (reporte.getCantidadHoras() > Reporte.getMaxHoras()) {
            String message = String.format("No puede ingresar más de %d horas.", Reporte.getMaxHoras());
            throw new InvalidHoursException(message);
        }
        if (reporte.getCantidadHoras() < 0) throw new InvalidHoursException("No puede ingresar horas negativas.");

        Recurso recurso = recursoService.findById(reporte.getLegajo());
        if (recurso == null) throw new InvalidLegajoException("No existe recurso con el legajo ingresado.");
    }

    public Reporte createReporte(Reporte reporte) {
        checkReporteValido(reporte);

        return reporteRepository.save(reporte);
    }

    public ResponseEntity<Reporte> updateReporte(Reporte reporte, Long reporteId) {
        checkReporteValido(reporte);

        reporte.setReporteId(reporteId);
        reporteRepository.save(reporte);

        return ResponseEntity.ok().build();
    }

    public Optional<Reporte> findById(Long id) {
        return reporteRepository.findById(id);
    }

    public List<Reporte> getReportes() {
        return reporteRepository.findAll();
    }

    public List<Reporte> getReportesByRecurso(Long legajo) {
        return reporteRepository.findReporteByLegajo(legajo);
    }

    public List<Reporte> getReportesByProyecto(Long id) {
        return reporteRepository.findReporteByProyectoId(id);

    }

    public List<Reporte> getReportesByTarea(Long id) {
        return reporteRepository.findReporteByTareaId(id);
    }

    public List<Reporte> getReportesByRange(LocalDate inicio, LocalDate fin) {
        if (inicio.compareTo(fin) > 0) throw new InvalidDateRangeException("La fecha final debe ser mayor a la incial.");

        return reporteRepository.findReporteByFechaBetween(inicio, fin);
    }
}