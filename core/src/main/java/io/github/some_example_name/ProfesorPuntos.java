package io.github.some_example_name;

public class ProfesorPuntos extends Profesor {
    private int puntos;

    public ProfesorPuntos(String nombre, int frecuencia, int probabilidad, int puntos) {
        super(nombre, frecuencia, probabilidad);
        this.puntos = puntos;
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.incrementarPuntaje(puntos);
        System.out.println(nombre + " otorga " + puntos + " puntos.");
    }
 
    @Override
    public void aplicarEfecto(Jugador jugador, ControladorJuego controlador) {
        aplicarEfecto(jugador);  
    }
    @Override
    public String getTipo() {
        return "ProfesorPuntos";  
    }
}
