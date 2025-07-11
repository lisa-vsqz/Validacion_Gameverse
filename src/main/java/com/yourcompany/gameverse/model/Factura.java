package com.yourcompany.gameverse.model;

import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.ws.rs.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

import calculadores.*;
import lombok.*;

@Entity
@Getter
@Setter
@View(members =
    "anyo, numero, fecha;" +
    "detalles { detalles };" +
    "observaciones;" +
    "iva, importeTotal, descuentoTotal, importeConDescuento"
)
@Tab(properties = 
    "anyo, numero, fecha, importeTotal, descuentoTotal, importeConDescuento"
)
public class Factura extends Identificable {

    @Column(length = 4)
    @DefaultValueCalculator(CurrentYearCalculator.class)
    int anyo;

    @Column(length = 6)
    @DefaultValueCalculator(
        value = CalculadorSiguienteNumeroParaAnyo.class,
        properties = @PropertyValue(name = "anyo")
    )
    int numero;

    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class)
    LocalDate fecha;

    @ElementCollection
    @ListProperties(
        "videojuego.titulo, videojuego.precio, cantidad, precioPorUnidad, importe"
    )
    Collection<DetalleFactura> detalles;

    @Stereotype("MEMO")
    String observaciones;

    @Digits(integer = 2, fraction = 0)
    @DefaultValue("12")
    BigDecimal porcentajeIVA;

    @ReadOnly
    @Money
    @Calculation("sum(detalles.importe) * porcentajeIVA / 100")
    BigDecimal iva;

    @ReadOnly
    @Money
    @Calculation("sum(detalles.importe) + iva")
    BigDecimal importeTotal;

    @ReadOnly
    @Money
    @Depends("detalles")
    public BigDecimal getDescuentoTotal() {
        BigDecimal descuento = BigDecimal.ZERO;
        if (detalles == null) return descuento;
        for (DetalleFactura d : detalles) {
            BigDecimal linea = d.getImporte();
            BigDecimal descLinea = BigDecimal.ZERO;
            int cantidad = d.getCantidad();
            if (cantidad > 10) {
                descLinea = linea.multiply(new BigDecimal("0.05"));
            } else if (cantidad > 5) {
                descLinea = linea.multiply(new BigDecimal("0.02"));
            }
            descuento = descuento.add(descLinea);
        }
        return descuento;
    }

    @ReadOnly
    @Money
    @Depends("importeTotal, descuentoTotal")
    @Calculation("importeTotal - descuentoTotal")
    public BigDecimal getImporteConDescuento() {
        return null;
    }

    @PrePersist
    @PreUpdate
    private void validarStock() {
        if (detalles == null) return;

        for (DetalleFactura detalle : detalles) {
            if (detalle.getVideojuego() == null) continue;

            int stockDisponible = detalle.getVideojuego().getStock();
            if (detalle.getCantidad() > stockDisponible) {
                throw new javax.validation.ValidationException(
                    "No hay suficiente stock para el juego: " +
                    detalle.getVideojuego().getTitulo() +
                    " (Stock disponible: " + stockDisponible + ")"
                );
            }
        }
    }

    @PostPersist
    @PostUpdate
    private void actualizarStock() {
        if (detalles == null) return;

        for (DetalleFactura detalle : detalles) {
            if (detalle.getVideojuego() == null) continue;

            Videojuego juego = detalle.getVideojuego();
            juego.setStock(juego.getStock() - detalle.getCantidad());
            XPersistence.getManager().merge(juego);
        }
    }
}
