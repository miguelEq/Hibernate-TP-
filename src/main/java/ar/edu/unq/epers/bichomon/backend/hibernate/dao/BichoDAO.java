package ar.edu.unq.epers.bichomon.backend.hibernate.dao;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import java.util.Collection;

public interface BichoDAO {

    void guardar(Bicho bicho);

    Bicho recuperar(Long id);

    void actualizar(Bicho bicho);




}
