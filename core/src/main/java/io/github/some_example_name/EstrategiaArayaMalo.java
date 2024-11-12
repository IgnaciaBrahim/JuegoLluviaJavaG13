package io.github.some_example_name;

public class EstrategiaArayaMalo implements EstrategiaProfesor{
    @Override
    public void efectoProfesor(Jugador jugador, Tarro tarro, ProfesorAraya aa, ProfesoraLaurita ll) {
        tarro.sonidoExtrano();
        if (!jugador.esInmune() && Math.random() < 0.9) 
        { 
        	// 90% de probabilidad si no es inmune
            jugador.cambiarPuntaje( -1 * (jugador.getPuntaje() / 2)); // Divide el puntaje a la mitad
        }
        aa.aplicarEfecto(jugador, null);
        //Dividio y no vencio :c
    }
}
