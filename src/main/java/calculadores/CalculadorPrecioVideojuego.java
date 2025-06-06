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
        if (idVideojuego == null) return BigDecimal.ZERO;
        Videojuego juego = XPersistence.getManager()
            .find(Videojuego.class, idVideojuego);
        return juego.getPrecio();
    }
}