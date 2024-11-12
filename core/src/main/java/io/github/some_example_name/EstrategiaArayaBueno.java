package io.github.some_example_name;

public class EstrategiaArayaBueno implements EstrategiaProfesor {
    @Override
    public void efectoProfesor(Jugador jugador, Tarro tarro, ProfesorAraya aa, ProfesoraLaurita ll) {
        tarro.sonidoBueno();
        jugador.cambiarPuntaje(15);//Cuando araya es bueno te da puntaje
        //Si no lo has recogido más de 3 veces :)
        aa.aplicarEfecto(jugador, null);
    }
}