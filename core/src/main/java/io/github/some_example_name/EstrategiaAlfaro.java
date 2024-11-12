package io.github.some_example_name;

public class EstrategiaAlfaro implements EstrategiaProfesor {
	
    @Override
    public void efectoProfesor(Jugador jugador, Tarro tarro, ProfesorAraya aa, ProfesoraLaurita ll) {
        tarro.sonidoBueno();
        jugador.cambiarPuntaje(10);
    }
}