package calculadores;

import java.math.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import com.yourcompany.gameverse.model.*;

import lombok.*;

public class CalculadorPrecioVideojuego implements ICalculator {
    
    @Getter @Setter
    String idVideojuego;
    
    @Override
    public Object calculate() throws Exception {
        if (idVideojuego == null || idVideojuego.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        try {
            Videojuego juego = XPersistence.getManager()
                .find(Videojuego.class, idVideojuego);
            
            if (juego == null || juego.getPrecio() == null) {
                return BigDecimal.ZERO;
            }
            
            return juego.getPrecio();
            
        } catch (Exception e) {
            System.err.println("Error al calcular precio: " + e.getMessage());
            return BigDecimal.ZERO;
        }
    }
}