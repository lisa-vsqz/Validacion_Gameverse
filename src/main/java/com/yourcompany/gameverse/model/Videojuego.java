package com.yourcompany.gameverse.model;


import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Videojuego extends Identificable {
    
    @Column(length=50) @Required
    String titulo;
    
    @Column(length=500) @Stereotype("MEMO")
    String descripcion;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @DescriptionsList
    Desarrollador desarrollador;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @DescriptionsList
    Categoria categoria;
    
    @Money
    BigDecimal precio;
    
    @Column
    int stock;
    
    @Column(length=4)
    int añoLanzamiento;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @DescriptionsList
    Plataforma plataforma;
    
    @Files
    @Column(length=32)
    String imagenes;
    

    

}