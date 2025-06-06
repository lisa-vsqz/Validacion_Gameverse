package com.yourcompany.gameverse.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Plataforma extends Identificable {
    
    @Column(length=50) @Required
    String nombre;

}