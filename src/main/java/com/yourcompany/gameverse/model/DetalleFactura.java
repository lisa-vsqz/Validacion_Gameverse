package com.yourcompany.gameverse.model;

import java.math.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;

import calculadores.*;
import lombok.*;
@Embeddable @Getter @Setter
public class DetalleFactura {
    
    @Min(1)
    @Required
    int cantidad;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @DescriptionsList(descriptionProperties="titulo,precio")
    Videojuego videojuego;
    
    @Money
    @Depends("precioPorUnidad, cantidad")
    public BigDecimal getImporte() {
        if (precioPorUnidad == null || cantidad <= 0) return BigDecimal.ZERO;
        return new BigDecimal(cantidad).multiply(precioPorUnidad);
    }
    
    @DefaultValueCalculator(
        value=CalculadorPrecioVideojuego.class,
        properties=@PropertyValue(name="idVideojuego", from="videojuego.id")
    )
    @Money
    BigDecimal precioPorUnidad;
}