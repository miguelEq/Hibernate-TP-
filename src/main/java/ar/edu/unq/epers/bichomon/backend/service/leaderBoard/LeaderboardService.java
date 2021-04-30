package ar.edu.unq.epers.bichomon.backend.service.leaderBoard;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import java.util.List;

public interface LeaderboardService {
    List<Entrenador> campeones();
    Especie especieLider();
    List<Entrenador>lideres();
}
