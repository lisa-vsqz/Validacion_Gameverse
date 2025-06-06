package com.yourcompany.gameverse.model;


import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Desarrollador extends Identificable {
    
    @Column(length=50) @Required
    String nombre;
    
    @OneToMany(mappedBy="desarrollador")
    @ListProperties("titulo, precio, stock")
    Collection<Videojuego> videojuegos;
}