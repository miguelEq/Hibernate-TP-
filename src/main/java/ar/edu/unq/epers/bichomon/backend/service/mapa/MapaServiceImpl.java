package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.hibernate.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.hibernate.dao.UbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.TransactionRunner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import static ar.edu.unq.epers.bichomon.backend.service.runner.TransactionRunner.run;

public class MapaServiceImpl implements MapaService {
    private EntrenadorDAO entrenadorDAO;
    private UbicacionDAO ubicacionDAO;

    public MapaServiceImpl(){

    }
    public MapaServiceImpl(EntrenadorDAO entrenadorDAO, UbicacionDAO ubicacionDAO){
        this.entrenadorDAO = entrenadorDAO;
        this.ubicacionDAO = ubicacionDAO;
    }
    @Override
    public void mover(String entrenador, String ubicacion) {
        run(() ->{
            Entrenador entrenador1 = entrenadorDAO.recuperarEntrenador(entrenador);
            Ubicacion nuevaUbicacion = ubicacionDAO.recuperarUbicacionNombre(ubicacion);
            entrenador1.setUbicacionActual(nuevaUbicacion);
            entrenadorDAO.actualizar(entrenador1);
            //ubicacionDAO.actualizar(nuevaUbicacion);
        });

    }


    @Override
    public int cantidadEntrenadores(String ubicacion) {
       return run(() -> this.ubicacionDAO.cantDeEntrenadores(ubicacion));
    }

    @Override
    public Bicho campeon(String dojo) {
       return  run(() -> this.ubicacionDAO.campeon(dojo));
    }

    @Override
    public Bicho campeonHistorico(String dojo) {
        return run(()-> this.ubicacionDAO.campeonHistorico(dojo));
    }

}
