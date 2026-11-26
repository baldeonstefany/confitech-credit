package com.confitech.credit.dto;

import com.confitech.credit.model.EstadoCredito;
import com.confitech.credit.model.ResultadoEvaluacion;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CreditEvaluationResponse(
        EstadoCredito estado,
        List<String> motivos,
        BigDecimal montoAprobado,
        BigDecimal tasaMensual,
        Integer plazoMeses,
        BigDecimal cuotaMensual) {

    public static CreditEvaluationResponse desde(ResultadoEvaluacion r) {
        return new CreditEvaluationResponse(r.estado(), r.motivos(), r.montoAprobado(),
                r.tasaMensual(), r.plazoMeses(), r.cuotaMensual());
    }
}
