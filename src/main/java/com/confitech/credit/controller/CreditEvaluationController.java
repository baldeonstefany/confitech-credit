package com.confitech.credit.controller;

import com.confitech.credit.dto.*;
import com.confitech.credit.model.*;
import com.confitech.credit.service.EvaluacionCreditoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/creditos")
public class CreditEvaluationController {
    private final EvaluacionCreditoService service;

    public CreditEvaluationController(
            EvaluacionCreditoService service
    ) {
        this.service = service;
    }

    @PostMapping("/evaluacion")
    public CreditEvaluationResponse evaluar(@Valid @RequestBody CreditEvaluationRequest r) {
        var cliente = new Cliente(r.dni(), r.nombre(), r.edad(), r.ingresoNeto());
        var solicitud = new SolicitudCredito(r.montoSolicitado(), r.plazoMeses());
        var reporte = new ReporteExperian(r.scoreCrediticio(), r.clasificacionSbs(), r.deudaMorosaActual());
        return CreditEvaluationResponse.desde(service.evaluar(cliente, solicitud, reporte));
    }
}
