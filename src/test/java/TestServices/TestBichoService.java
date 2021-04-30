package TestServices;

import ar.edu.unq.epers.bichomon.backend.hibernate.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.dao.DataDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.impl.HibernateDataDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.resultadoCombate.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorServiceImpl;
import jdk.nashorn.internal.ir.EmptyNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestBichoService {
    private DataServiceImpl dataImpl;
    private EntrenadorDAO daoEntrenador;
    private BichoDAO daoBicho;
    private DataDAO daoData;
    private BichoServiceImpl bichoService;
    private Ubicacion ubi;
    private EntrenadorServiceImpl entrenadorService;

    @Before
    public void crearModelo(){
        ubi = new Guarderia();
        dataImpl = new DataServiceImpl();
        daoData = new HibernateDataDAO();
        daoBicho = new HibernateBichoDAO();
        daoEntrenador = new HibernateEntrenadorDAO();
        bichoService = new BichoServiceImpl(daoEntrenador,daoBicho,daoData);
        dataImpl.crearSetDatosIniciales();
        entrenadorService = new EntrenadorServiceImpl(daoEntrenador);
    }

   @After
    public void eliminarALoF(){
      this.dataImpl.eliminarDatos();
   }




   //BUSQUEDAD
    @Test
    public void Busquedad_no_se_encuetra_bichomon(){
        try {
            bichoService.buscar("Ash");
        }catch (RuntimeException e){
            assertThat(e.getMessage(),is ("No se encontro Bicho"));
        }

    }


    @Test
    public void buscoBicho(){
        Entrenador ash = entrenadorService.buscarEntrenador("Ash");
        Assert.assertEquals(2,ash.cantBichos());
        bichoService.buscar("Ash");
        ash = entrenadorService.buscarEntrenador("Ash");
        Assert.assertEquals(3,ash.cantBichos());

    }



    //ABANDONO
    @Test
    public void Ash_abandona_un_Bicho(){
        Entrenador ash = entrenadorService.buscarEntrenador("Ash");
        Assert.assertEquals(2,ash.cantBichos());
        bichoService.abandonar("Ash",1);
        ash = entrenadorService.buscarEntrenador("Ash");
        Assert.assertEquals(1,ash.cantBichos());
    }

    @Test
    public void Bronck_no_puede_abandonar_bicho_porque_no_le_pertenece_y_no_esta_en_la_guarderia(){
        try {
            bichoService.abandonar("Brock",1);
        }catch (RuntimeException e){
            assertThat(e.getMessage(),is("No se encuentra en guarderia"));
        }
    }



   //ENVOLUCIONAR
    @Test
    public void evolucionoBicho(){

        Long id = new Long(1);
        Bicho pika = bichoService.buscarBicho(id);
        Assert.assertEquals(pika.getEspecie().getNombre(),"Pikachu");
        bichoService.evolucionar("Ash",1);
        Bicho raichu = bichoService.buscarBicho(id);
        Assert.assertEquals(raichu.getEspecie().getNombre(),"Raichu");
        Assert.assertEquals(raichu.getId(),pika.getId());


     }

     //DUELO
     @Test
    public void El_bicho_Brock_es_el_ganador(){

        ResultadoCombate resultadoCombate = bichoService.duelo("Brock",4);
        Long id = new Long(4);
        Assert.assertEquals(resultadoCombate.getGanador().getId(),id);



     }

    @Test
    public void Ash_no_se_encuentra_en_Dojo(){
        try {
            bichoService.duelo("Ash",1);
        }catch (RuntimeException e){
            assertThat(e.getMessage(),is("no se puede combatir"));
        }
    }
    @Test
    public void Brock_es_el_ganador_del_duelo(){
        ResultadoCombate resultadoCombate = bichoService.duelo("Brock",4);
        Long i = new Long(4);
        assertEquals(resultadoCombate.getGanador().getId(),i);
    }



}
