package ar.edu.unq.epers.bichomon.backend.hibernate.dao;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

import java.util.Collection;

public interface EntrenadorDAO {

    void guardar(Entrenador entrenador);

    Entrenador recuperarEntrenador(String nombre);

    void actualizar(Entrenador entrenador);




}
