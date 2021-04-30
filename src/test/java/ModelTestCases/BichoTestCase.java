package ModelTestCases;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.condicionesEvolucion.CondBasadaEnVictoria;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

public class BichoTestCase {
    private Bicho pikachu;
    private Especie pika;
    private Especie raichiu;

    @Before
    public void setUp() {
        pika = new Especie("pika", TipoBicho.ELECTRICIDAD);
        raichiu = new Especie("raichu", TipoBicho.ELECTRICIDAD);
        pikachu = new Bicho(pika);
        pika.setEspecieSiguiente(raichiu);
    }

    @Test
    public void evolucionar() {
        Assert.assertEquals (pikachu.getEspecie().getNombre(),"pika");
        Entrenador ash = new Entrenador();

        pikachu.evolucionar(ash);
        Assert.assertEquals( raichiu.getNombre(),pikachu.getEspecie().getNombre());
    }

    @Test
    public void especieRaiz(){
        Assert.assertEquals(pikachu.getEspecie().especieRaiz().getNombre(),"pika");

    }

    @Test
    public void bicho_nunca_abandonado(){
        Assert.assertEquals(pikachu.getExEntrenadores().size(),0);
    }
}



