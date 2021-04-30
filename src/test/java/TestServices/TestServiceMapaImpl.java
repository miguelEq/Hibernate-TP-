package TestServices;

import ar.edu.unq.epers.bichomon.backend.hibernate.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.dao.UbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.impl.HibernateUbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionServiceImpl;
import com.sun.xml.bind.v2.TODO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestServiceMapaImpl {
    private MapaService mapaServiceImpl;
    private UbicacionServiceImpl ubicacionService;
    private EntrenadorDAO entrenadorDAO;
    private UbicacionDAO ubicacionDAO;
    private EntrenadorServiceImpl entrenadorService;
    private DataServiceImpl dataService;


    @Before
    public void setUp(){
        entrenadorDAO = new HibernateEntrenadorDAO();
        ubicacionDAO =  new HibernateUbicacionDAO();
        ubicacionService = new UbicacionServiceImpl(ubicacionDAO);
        mapaServiceImpl = new MapaServiceImpl(entrenadorDAO,ubicacionDAO);
        entrenadorService = new EntrenadorServiceImpl(entrenadorDAO);
        dataService = new DataServiceImpl();
        dataService.crearSetDatosIniciales();
    }



    @Test
    public void Brock_se_mueve_a_la_Guarderia(){
        Entrenador brock = entrenadorService.buscarEntrenador("Brock");
        Assert.assertEquals(brock.getUbicacionActual().getNombre(),"Dojo");
        mapaServiceImpl.mover("Brock","Guarderia");
        Entrenador brockActualizado = entrenadorService.buscarEntrenador("Brock");
        Assert.assertEquals(brockActualizado.getUbicacionActual().getNombre(),"Guarderia");

    }

    @Test
    public void Cantidad_Entrenadores_Ubicacion(){
        mapaServiceImpl.mover("Brock","Guarderia");
        Assert.assertEquals(mapaServiceImpl.cantidadEntrenadores("Guarderia"),2);
        Assert.assertEquals(mapaServiceImpl.cantidadEntrenadores("Dojin"),1);
    }

    @Test
    public void Bicho_Campeon_Dojo(){
        long l = 5;
        Assert.assertEquals(mapaServiceImpl.campeon("Dojo").getEspecie().getNombre(),"Meowth");
    }
    @Test
    public void Bicho_Mas_Pro(){
        long lon = new Long(5);
        Assert.assertEquals(mapaServiceImpl.campeonHistorico("Dojo").getEspecie().getNombre(),"Meowth");
    }

    @After
    public void eliminarDatos(){
        dataService.eliminarDatos();
    }

}
