package com.confitech.credit.dto;

import com.confitech.credit.model.ClasificacionSbs;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreditEvaluationRequest(
        @NotBlank String dni,
        @NotBlank String nombre,
        @NotNull @PositiveOrZero Integer edad,
        @NotNull @PositiveOrZero BigDecimal ingresoNeto,
        @Positive BigDecimal montoSolicitado,
        @NotNull @Positive Integer plazoMeses,
        @NotNull @Min(0) @Max(1000) Integer scoreCrediticio,
        @NotNull ClasificacionSbs clasificacionSbs,
        @NotNull @PositiveOrZero BigDecimal deudaMorosaActual
) {
    public CreditEvaluationRequest {
        if (montoSolicitado == null) montoSolicitado = new BigDecimal("25000");
    }
}
