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
"detalles { detalles };" + 
"observaciones;" +
"iva, importeTotal, descuentoTotal, importeConDescuento"
)
@Tab(properties="anyo, numero, fecha, importeTotal, descuentoTotal, importeConDescuento")	
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
    BigDecimal importeTotal;
    
    @ReadOnly 
    @Money
    @Depends("detalles")            // Recalcula cuando cambien los detalles
    public BigDecimal getDescuentoTotal() {
        BigDecimal descuento = BigDecimal.ZERO;
        if (detalles == null) return descuento;
        for (DetalleFactura d : detalles) {
            BigDecimal linea = d.getImporte();
            BigDecimal descLinea = BigDecimal.ZERO;
            int cantidad = d.getCantidad();
            if (cantidad > 10) {
                // Más de 10 unidades → 5 %
                descLinea = linea.multiply(new BigDecimal("0.05"));
            }
            else if (cantidad > 5) {
                // Entre 6 y 10 → 2 %
                descLinea = linea.multiply(new BigDecimal("0.02"));
            }
            // < 6 → sin descuento
            descuento = descuento.add(descLinea);
        }
        return descuento;
    }

    
    @ReadOnly 	
    @Money
    @Depends("importeTotal, descuentoTotal")
    @Calculation("importeTotal - descuentoTotal")
    public BigDecimal getImporteConDescuento() {
        // OpenXava se encarga de calcular este campo basado en la expresión de cálculo
        return null; // Este getter es solo para la anotación @Calculation
    }
    
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