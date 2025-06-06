package com.yourcompany.gameverse.model;
 
import javax.persistence.*;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.*;

import lombok.*;

@Entity @Getter @Setter
public class Categoria extends Identificable {
    
    @Column(length=50) @Required
    String nombre;
    
    @Column(length=200)
    String descripcion;
}