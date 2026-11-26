package com.confitech.credit.model;

import java.math.BigDecimal;

public record ReporteExperian(
        int scoreCrediticio,
        ClasificacionSbs clasificacionSbs,
        BigDecimal deudaMorosaActual
) {}
