package com.aninfo;

import com.aninfo.model.Recurso;
import com.aninfo.model.Reporte;
import com.aninfo.service.RecursoService;
import com.aninfo.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class RRHHApp {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private RecursoService recursoService;

    public static void main(String[] args) {
        SpringApplication.run(RRHHApp.class, args);
    }

    @GetMapping("/recursos")
    public Collection<Recurso> getRecursos() {
        return recursoService.getRecursos();
    }

    @GetMapping("/recursos/{legajo}")
    public Recurso getRecurso(@PathVariable Long legajo) {
        return recursoService.findById(legajo);
    }

    @PostMapping("/recursos/{legajo}/reportes")
    public Reporte createReporte(@RequestBody Reporte reporte){
        return reporteService.createReporte(reporte);
    }

    @PutMapping("/reportes/{reporteId}")
    public ResponseEntity<Reporte> updateReporte(@RequestBody Reporte reporte, @PathVariable Long reporteId) {
        Optional<Reporte> reporteOptional = reporteService.findById(reporteId);

        if (reporteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return reporteService.updateReporte(reporte, reporteId);
    }

    @GetMapping("/reportes")
    public List<Reporte> getReportes() {
        return reporteService.getReportes();
    }

    @GetMapping("/reportes/{reporteId}")
    public Optional<Reporte> getReporte(@PathVariable Long reporteId) {
        return reporteService.findById(reporteId);
    }

    @GetMapping("/recursos/{legajo}/reportes")
    public List<Reporte> getReportesByRecurso(@PathVariable Long legajo) {
        return reporteService.getReportesByRecurso(legajo);
    }

    @GetMapping("/proyectos/{proyecto}/reportes")
    public List<Reporte> getReportesByProyecto(@PathVariable Long proyecto) {
        return reporteService.getReportesByProyecto(proyecto);
    }

    @GetMapping("/tareas/{tarea}/reportes")
    public List<Reporte> getReportesByTarea(@PathVariable Long tarea) {
        return reporteService.getReportesByTarea(tarea);
    }

    @GetMapping("/reportes/start={inicio}&end={fin}")
    public List<Reporte> getReportesByRange(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fin) {
        return reporteService.getReportesByRange(inicio, fin);
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
