package clasesTest;

import org.example.Avion;
import org.example.AvionComercial;
import org.example.AvionPrivado;
import org.example.CalculadorCombustible;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculadorCombustibleTest {
    int distancia = 100;

    @Test
    public void calcularCombustibleTest() {
        CalculadorCombustible calculadorCombustible = CalculadorCombustible.getInstance();

        Avion avionComercial = new AvionComercial(200, 30);
        Avion avionPrivado = new AvionPrivado(500, 10);
        Assertions.assertEquals(1250, calculadorCombustible.calcularCombustible(avionPrivado, distancia));
        Assertions.assertEquals(3000, calculadorCombustible.calcularCombustible(avionComercial, distancia));



    }
}




