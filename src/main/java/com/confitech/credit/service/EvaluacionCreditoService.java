package com.confitech.credit.service;

import com.confitech.credit.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public final class EvaluacionCreditoService {
    private static final BigDecimal TASA_ESTANDAR = new BigDecimal("0.035");
    private static final BigDecimal TASA_PREFERENCIAL = new BigDecimal("0.02");
    private static final BigDecimal LIMITE = new BigDecimal("0.35");

    public ResultadoEvaluacion evaluar(Cliente cliente, SolicitudCredito solicitud, ReporteExperian reporte) {
        validarEntrada(cliente, solicitud, reporte);
        List<String> motivos = new ArrayList<>();
        if (cliente.edad() < 21 || cliente.edad() > 65) {
            motivos.add("Edad fuera del rango permitido (21 a 65 años)");
        }
        if (reporte.clasificacionSbs() != ClasificacionSbs.NORMAL) {
            motivos.add("Clasificación SBS distinta de NORMAL");
        }
        if (reporte.deudaMorosaActual().compareTo(BigDecimal.ZERO) != 0) {
            motivos.add("Presenta deuda morosa actual");
        }

        BigDecimal tasa = null;
        if (reporte.scoreCrediticio() < 600) {
            motivos.add("Score crediticio insuficiente");
        } else {
            tasa = reporte.scoreCrediticio() < 700 ? TASA_ESTANDAR : TASA_PREFERENCIAL;
        }

        BigDecimal cuota = null;
        if (tasa != null) {
            BigDecimal monto = solicitud.montoSolicitado();
            BigDecimal meses = BigDecimal.valueOf(solicitud.plazoMeses());
            BigDecimal interes = monto.multiply(tasa);
            // Equivale a monto / meses + monto * tasa.
            BigDecimal numerador = monto.add(interes.multiply(meses));
            cuota = numerador.divide(meses, 2, RoundingMode.HALF_UP);
            // Comparación exacta, sin redondear la cuota para decidir.
            if (numerador.compareTo(cliente.ingresoNeto().multiply(LIMITE).multiply(meses)) > 0) {
                motivos.add("La cuota excede el 35% del ingreso");
            }
        }
        if (!motivos.isEmpty()) {
            return new ResultadoEvaluacion(EstadoCredito.RECHAZADO, motivos, null, null, null, null);
        }
        return new ResultadoEvaluacion(EstadoCredito.APROBADO, motivos, solicitud.montoSolicitado(), tasa, solicitud.plazoMeses(), cuota);
    }

    private void validarEntrada(Cliente c, SolicitudCredito s, ReporteExperian r) {
        if (c == null || s == null || r == null) throw new IllegalArgumentException("Datos obligatorios ausentes");
        if (c.dni() == null || c.dni().isBlank() || c.nombre() == null || c.nombre().isBlank())
            throw new IllegalArgumentException("DNI y nombre son obligatorios");
        if (c.ingresoNeto() == null || c.ingresoNeto().signum() < 0 || c.edad() < 0)
            throw new IllegalArgumentException("Edad o ingreso inválido");
        if (s.montoSolicitado() == null || s.montoSolicitado().signum() <= 0 || s.plazoMeses() <= 0)
            throw new IllegalArgumentException("Monto y plazo deben ser positivos");
        if (r.scoreCrediticio() < 0 || r.scoreCrediticio() > 1000 || r.clasificacionSbs() == null || r.deudaMorosaActual() == null || r.deudaMorosaActual().signum() < 0)
            throw new IllegalArgumentException("Datos de Experian inválidos");
    }
}
