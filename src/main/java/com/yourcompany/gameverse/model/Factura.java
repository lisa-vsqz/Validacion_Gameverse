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

@Entity @Getter @Setter
@View(members=
    "anyo, numero, fecha;" + 
    "detalles {" +
        "detalles;" +
    "}" +
    "observaciones;" +
    "importeTotal, iva"  // Cambiado de 'total' a 'importeTotal'
)
@Tab(properties="anyo, numero, fecha, importeTotal")
public class Factura extends Identificable {
    
    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class)
    int anyo;
    
    @Column(length=6)
    @DefaultValueCalculator(value=CalculadorSiguienteNumeroParaAnyo.class,
        properties=@PropertyValue(name="anyo"))
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
    
    @Digits(integer=2, fraction=0)
    @DefaultValue("12")
    BigDecimal porcentajeIVA;
    
    @ReadOnly @Money
    @Calculation("sum(detalles.importe) * porcentajeIVA / 100")
    BigDecimal iva;
    
    @ReadOnly @Money
    @Calculation("sum(detalles.importe) + iva")
    BigDecimal importeTotal;  // Usar este nombre en lugar de 'total'
    
    @PrePersist @PreUpdate
    private void validarStock() {
        if (detalles == null) return;
        
        for (DetalleFactura detalle : detalles) {
            if (detalle.getVideojuego() == null) continue;
            
            if (detalle.getCantidad() > detalle.getVideojuego().getStock()) {
                throw new javax.validation.ValidationException(
                    "No hay suficiente stock para el juego: " + 
                    detalle.getVideojuego().getTitulo()
                );
            }
        }
    }
    
    @PostPersist @PostUpdate
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