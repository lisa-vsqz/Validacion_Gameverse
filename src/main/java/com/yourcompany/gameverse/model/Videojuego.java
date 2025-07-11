package com.yourcompany.gameverse.model;

import java.math.*;

import javax.persistence.*;
import javax.ws.rs.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity 
@Getter @Setter 
public class Videojuego extends Identificable {
    
    @Column(length=50) 
    @Required
    String titulo;
    
    @Column(length=500) 
    @Stereotype("MEMO")
    String descripcion;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @DescriptionsList
    Desarrollador desarrollador;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @DescriptionsList
    Categoria categoria;
    
    @Money
    @Required
    BigDecimal precio;
    
    @Column
    @DefaultValue("0")
    Integer stock; // Cambié de int a Integer para permitir null
    
    @Column(length=4)
    @DefaultValue("2024")
    Integer añoLanzamiento; // Cambié de int a Integer para permitir null
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @DescriptionsList
    Plataforma plataforma;
    
    @Files
    @Column(length=32)
    String imagenes;
}