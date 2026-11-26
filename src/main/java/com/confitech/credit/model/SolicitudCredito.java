package com.confitech.credit.model;

import java.math.BigDecimal;

public record SolicitudCredito(
        BigDecimal montoSolicitado,
        int plazoMeses
) {}
