package com.confitech.credit.model;

import java.math.BigDecimal;

public record Cliente(
        String dni,
        String nombre,
        int edad,
        BigDecimal ingresoNeto
) {}
