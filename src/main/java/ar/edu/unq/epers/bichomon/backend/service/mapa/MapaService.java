package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

public interface MapaService {
    public void mover(String entrenador,String Ubicacion);
    public int cantidadEntrenadores(String ubicacion);
    public Bicho campeon(String Dojo);
    public Bicho campeonHistorico(String Dojo);
}
