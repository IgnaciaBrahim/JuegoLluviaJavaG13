package io.github.some_example_name;

public class EstrategiaCubillos implements EstrategiaProfesor {
    @Override
    public void efectoProfesor(Jugador jugador, Tarro tarro, ProfesorAraya aa, ProfesoraLaurita ll) {
        if (!jugador.esInmune()) {
            tarro.dañar();
            jugador.cambiarPuntaje(-30);
            jugador.cambiarVida(-1);
            jugador.activarTormentaCubillos(); // Activa la tormenta a través del jugador
        }
    }
}
