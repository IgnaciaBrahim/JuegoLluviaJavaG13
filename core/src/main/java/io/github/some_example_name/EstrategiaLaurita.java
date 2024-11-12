package io.github.some_example_name;

public class EstrategiaLaurita implements EstrategiaProfesor {
    @Override
    public void efectoProfesor(Jugador jugador, Tarro tarro, ProfesorAraya aa, ProfesoraLaurita ll) {
        tarro.sonidoEspecial();
        jugador.cambiarPuntaje(25);
        ll.aplicarEfecto(jugador, null);
    }
}