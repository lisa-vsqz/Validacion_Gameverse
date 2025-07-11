package com.yourcompany.gameverse.model;

import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

import calculadores.*;
import lombok.*;

@Entity 
@Getter @Setter
@View(members=
    "anyo, numero, fecha;" +
    "detalles { detalles };" +
    "observaciones;" + 
    "importeSubtotal, descuentoTotal, importeConDescuento, iva, importeTotal"
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
        "videojuego.titulo, precioPorUnidad, cantidad, importe"
    )
    Collection<DetalleFactura> detalles;
    
    @Stereotype("MEMO")
    String observaciones;
    
    // Importe subtotal - suma de todos los importes de detalles
    @Money
    @Depends("detalles")
    public BigDecimal getImporteSubtotal() {
        if (detalles == null || detalles.isEmpty()) return BigDecimal.ZERO;
        
        BigDecimal subtotal = BigDecimal.ZERO;
        for (DetalleFactura detalle : detalles) {
            BigDecimal importe = detalle.getImporte();
            if (importe != null) {
                subtotal = subtotal.add(importe);
            }
        }
        return subtotal;
    }
    
    // Descuento total calculado
    @Money
    @Depends("detalles")
    public BigDecimal getDescuentoTotal() {
        if (detalles == null || detalles.isEmpty()) return BigDecimal.ZERO;
        
        BigDecimal descuento = BigDecimal.ZERO;
        for (DetalleFactura detalle : detalles) {
            BigDecimal importeLinea = detalle.getImporte();
            if (importeLinea == null) continue;
            
            int cantidad = detalle.getCantidad();
            BigDecimal descuentoLinea = BigDecimal.ZERO;
            
            if (cantidad > 10) {
                // Más de 10 unidades → 5%
                descuentoLinea = importeLinea.multiply(new BigDecimal("0.05"));
            } else if (cantidad > 5) {
                // Entre 6 y 10 → 2%
                descuentoLinea = importeLinea.multiply(new BigDecimal("0.02"));
            }
            
            descuento = descuento.add(descuentoLinea);
        }
        return descuento;
    }
    
    // Importe con descuento
    @Money
    @Depends("importeSubtotal, descuentoTotal")
    public BigDecimal getImporteConDescuento() {
        BigDecimal subtotal = getImporteSubtotal();
        BigDecimal descuento = getDescuentoTotal();
        
        if (subtotal == null) subtotal = BigDecimal.ZERO;
        if (descuento == null) descuento = BigDecimal.ZERO;
        
        return subtotal.subtract(descuento);
    }
    
    // IVA (12% fijo sobre importe con descuento)
    @Money
    @Depends("importeConDescuento")
    public BigDecimal getIva() {
        BigDecimal importeConDesc = getImporteConDescuento();
        if (importeConDesc == null) return BigDecimal.ZERO;
        
        return importeConDesc.multiply(new BigDecimal("0.12"));
    }
    
    // Importe total final
    @Money
    @Depends("importeConDescuento, iva")
    public BigDecimal getImporteTotal() {
        BigDecimal importeConDesc = getImporteConDescuento();
        BigDecimal iva = getIva();
        
        if (importeConDesc == null) importeConDesc = BigDecimal.ZERO;
        if (iva == null) iva = BigDecimal.ZERO;
        
        return importeConDesc.add(iva);
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