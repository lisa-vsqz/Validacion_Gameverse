package com.yourcompany.gameverse.model;

import java.math.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;

import lombok.*;

@Embeddable
@Getter
@Setter
public class DetalleFactura {

    @Min(1)
    @Required
    int cantidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList(descriptionProperties = "titulo,precio")
    Videojuego videojuego;

    @Money
    @Depends("precioPorUnidad, cantidad")
    public BigDecimal getImporte() {
        if (getPrecioPorUnidad() == null || cantidad <= 0) return BigDecimal.ZERO;
        return new BigDecimal(cantidad).multiply(getPrecioPorUnidad());
    }

    @Money
    @Depends("videojuego")
    public BigDecimal getPrecioPorUnidad() {
        if (videojuego == null) return BigDecimal.ZERO;
        return videojuego.getPrecio();
    }
}