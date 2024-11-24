package io.github.some_example_name;

public class ProfesorAlfaro extends Profesor {

    public ProfesorAlfaro() {
        super("Alfaro", 20, 30);
    }

    @Override
    public void aplicarEfecto(Jugador jugador, ControladorJuego controlador) {
        // Implementa el efecto que Alfaro tiene en el jugador
        jugador.cambiarPuntaje(10); // Por ejemplo, suma 10 puntos
        // Puedes añadir otros efectos según tu diseño
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        aplicarEfecto(jugador, null); 
    }

    @Override
    public String getTipo() {
        return "Alfaro";
    }
}
