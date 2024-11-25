package io.github.some_example_name;

public class EstrategiaArayaBueno implements EstrategiaProfesor {
    @Override
    public void efectoProfesor(Jugador jugador, Tarro tarro, ProfesorAraya aa, ProfesoraLaurita ll) {
        tarro.sonidoBueno();
        jugador.cambiarPuntaje(15);
    }
}

