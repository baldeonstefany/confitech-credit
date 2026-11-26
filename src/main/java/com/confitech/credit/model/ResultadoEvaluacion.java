package com.confitech.credit.model;

import java.math.BigDecimal;
import java.util.List;

public record ResultadoEvaluacion(
        EstadoCredito estado,
        List<String> motivos,
        BigDecimal montoAprobado,
        BigDecimal tasaMensual,
        Integer plazoMeses,
        BigDecimal cuotaMensual)
{
    public ResultadoEvaluacion {
        motivos = List.copyOf(motivos);
    }
}
