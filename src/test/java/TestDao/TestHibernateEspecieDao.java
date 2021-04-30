package TestDao;

import ar.edu.unq.epers.bichomon.backend.hibernate.dao.DataDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.serviceHibernate.ServiceHibernate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestHibernateEspecieDao extends TestAbstract{
    private EspecieDAO especieDAO;
    private Especie raichu;


    @Before
    public void setUpEspecieDao(){
        especieDAO = new HibernateEspecieDAO();
        serviceHibernate.setEspecieDAO(especieDAO);
        raichu = new Especie("Raichu", TipoBicho.ELECTRICIDAD);


    }
    @Test
    public void guardo_recupero_y_actualizo_a_raichu(){
        serviceHibernate.guardarEspecie(raichu);
        Especie raichuRecuperado = serviceHibernate.recuperarEspeciePorId(new Long(1));
        Assert.assertEquals(raichuRecuperado.getNombre(),raichu.getNombre());
        Assert.assertEquals(raichuRecuperado.getTipo(),raichu.getTipo());
        raichuRecuperado.setPeso(100);
        serviceHibernate.actualizarEspecie(raichuRecuperado);
        Especie raichuActualizado = serviceHibernate.recuperarEspeciePorId(new Long(1));
        Assert.assertEquals(raichuActualizado.getPeso(),100);
        Assert.assertEquals(raichuRecuperado.getNombre(),raichu.getNombre());
        Assert.assertEquals(raichuRecuperado.getTipo(),raichu.getTipo());
    }

    @Test
    public void guardo_recupero_por_nombre_y_actualizo_a_raichu(){
        serviceHibernate.guardarEspecie(raichu);
        Especie raichuRecuperado = serviceHibernate.recuperarEspeciePorNombre("Raichu");
        Assert.assertEquals(raichuRecuperado.getNombre(),raichu.getNombre());
        Assert.assertEquals(raichuRecuperado.getTipo(),raichu.getTipo());
        raichuRecuperado.setPeso(100);
        serviceHibernate.actualizarEspecie(raichuRecuperado);
        Especie raichuActualizado = serviceHibernate.recuperarEspeciePorNombre("Raichu");
        Assert.assertEquals(raichuActualizado.getPeso(),100);
        Assert.assertEquals(raichuRecuperado.getNombre(),raichu.getNombre());
        Assert.assertEquals(raichuRecuperado.getTipo(),raichu.getTipo());
    }

    @Test
    public void populares(){
        dataServiceImpl.crearSetDatosIniciales();
        List<Especie> populares = serviceHibernate.especiesPopulares();
        Assert.assertEquals(4,populares.size());
        Assert.assertEquals("Avion",populares.get(0).getNombre());
        Assert.assertEquals("Terrakion",populares.get(1).getNombre());
        Assert.assertEquals("Pikachu",populares.get(2).getNombre());
        Assert.assertEquals("Meowth",populares.get(3).getNombre());
    }
    @Test
    public void impopulares(){
        //En la Guarderia hay 6 bichos, 3 de especie Terrakion, 2 de especie Pikachu , 1 de especie Avion
        dataServiceImpl.crearSetDatosIniciales();
        List<Especie> impopulares = serviceHibernate.especiesImpopulares();
        Assert.assertEquals(3,impopulares.size());
        Assert.assertEquals("Terrakion",impopulares.get(0).getNombre());
        Assert.assertEquals("Pikachu",impopulares.get(1).getNombre());
        Assert.assertEquals("Avion",impopulares.get(2).getNombre());



    }
}
